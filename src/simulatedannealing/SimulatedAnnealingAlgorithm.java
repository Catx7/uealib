package simulatedannealing;

import simulatedannealing.ChartTracer.Type;
import core.Generator;
import core.Solution;

public class SimulatedAnnealingAlgorithm <T extends Solution> {

	private int iterationsPerStage;
	private SimulatedAnnealingContext<T> ctx;
	private ChartTracer tracer;
	private ITransitionCriteria transitionCriteria;
	private IStoppingCriteria stoppingCriteria;
	

	public SimulatedAnnealingAlgorithm(IEvaluator e,
			IInitializator<T> i, IGenerator<T> g) {
		
		this(e,i,g,0.98);
		

	}
	
	public SimulatedAnnealingAlgorithm(IEvaluator e,
			IInitializator<T> i, IGenerator<T> g, double c) {
		
		tracer = null;
		ctx = new SimulatedAnnealingContext<T>();

		this.transitionCriteria = new MetropolisRule(ctx);
		this.stoppingCriteria = new StabilyzedStoppingCriteria(20, ctx);

		ctx.setGenerator(g);
		ctx.setInitializator(i);
		ctx.setEvaluator(e);

		ctx.initTemperatureShedule(c);
		iterationsPerStage = 800; 

	}

	public void setIterationsPerStage(int iterationsPerStage) {
		this.iterationsPerStage = iterationsPerStage;
	}
	
	public void setChartTracer(ChartTracer tracer) {
		this.tracer = tracer;
	}

	public T solve() {
		if(tracer != null)
			tracer.timeStart();
		T currentSolution = this.ctx.getInitializator().getInitialSolution();
		int currentIteration = 0;
		
		
		while (!stoppingCriteria.isSatisfied()) {
			boolean nochange = true;
			for (int i = 0; i < iterationsPerStage; ++i) {
				T nextSolution = ctx.getGenerator().getNext(currentSolution);

				if (transitionCriteria.isSatisfied(currentSolution, nextSolution)) {
					currentSolution = nextSolution;
					nochange = false;
				}
			}
			if (nochange) {
				ctx.incCount();
			}

			else {
				ctx.countToZero();
			}
			
			if(tracer!=null) {
				if(tracer.getType() == Type.IterationToFitness)
					tracer.Trace(currentIteration, ctx.getEvaluator().evaluate(
							currentSolution));
			}
				
			
			System.out.println(ctx.getEvaluator().evaluate(
					currentSolution));
			
			ctx.getShedule().anneal();
			currentIteration++;
		}
		if(tracer != null)
			tracer.timeEnd();

		return currentSolution;
	}
}
