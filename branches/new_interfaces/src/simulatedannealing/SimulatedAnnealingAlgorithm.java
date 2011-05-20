package simulatedannealing;

import simulatedannealing.ChartTracer.Type;
import sun.security.x509.AVA;
import core.Generator;
import core.Solution;

public class SimulatedAnnealingAlgorithm <T extends Solution> {

	private int iterationsPerStage;
	private SimulatedAnnealingContext ctx;
	private ChartTracer tracer;
	private ITransitionCriteria transitionCriteria;
	private IStoppingCriteria stoppingCriteria;
	private ITemperatureShedule temperatureShedule;
	private IEvaluator<T> evaluator;
	private IInitializator<T> initializator;
	private IGenerator<T> generator;

	public SimulatedAnnealingAlgorithm(IEvaluator<T> evaluator,
			IInitializator<T> initializator, IGenerator<T> generator, ITemperatureShedule temperatureShedule,
			SimulatedAnnealingContext ctx) {
		
		tracer = null;
		this.initializator = initializator;
		this.generator = generator;
		this.evaluator = evaluator;
		this.ctx = ctx;
		
		this.temperatureShedule = temperatureShedule;
		this.transitionCriteria = new MetropolisRule(temperatureShedule, evaluator);
		this.stoppingCriteria = new StabilyzedStoppingCriteria(20, ctx);

		iterationsPerStage = 800; 

	}
	
	
	public SimulatedAnnealingAlgorithm(IEvaluator<T> e,
			IInitializator<T> i, IGenerator<T> g) {
		this(e,i,g,0.98);
	}
	
	@Deprecated
	public SimulatedAnnealingAlgorithm(IEvaluator<T> evaluator,
			IInitializator<T> initializator, IGenerator<T> generator, double alpha) {
				
		this(evaluator, initializator, generator, new TemperatureShedule(
				new DefaultInitialTemperatureProvider(initializator, generator, evaluator), 
				new GeometricAnnealProvider(alpha)),
			new SimulatedAnnealingContext());

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
		
		ctx.statistic.clear();
		
		
		T currentSolution = initializator.getInitialSolution();
		int currentIteration = 0;
		
		
		while (!stoppingCriteria.isSatisfied()) {
			boolean nochange = true;
			ctx.statistic.clear();
			
			for (int i = 0; i < iterationsPerStage; ++i) {
				T nextSolution = generator.getNext(currentSolution);

				if (transitionCriteria.isSatisfied(currentSolution, nextSolution)) {
					currentSolution = nextSolution;
					nochange = false;
					
					//if(i%(iterationsPerStage*0.05) == 0) {
						ctx.statistic.add(evaluator.evaluate(currentSolution));
					//}
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
					tracer.Trace(currentIteration, evaluator.evaluate(
							currentSolution));
			}
				
			
			System.out.println(evaluator.evaluate(
					currentSolution));
			
			temperatureShedule.anneal();
			currentIteration++;
		}
		if(tracer != null)
			tracer.timeEnd();

		return currentSolution;
	}
}
