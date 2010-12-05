package simulatedannealing;

import core.Algorithm;
import core.Generator;
import core.Initializator;
import core.Solution;

public class SimulatedAnnealingAlgorithm extends Algorithm<GenerationList> {
	
	private int iterationsPerStage;
	public SimulatedAnnealingAlgorithm(Evaluator e, Initializator<GenerationList> i, 
													Generator<GenerationList> g) {
		super(i, g, new StabilyzedStoppingCriteria(20), null, new MetropolisRule());
		
		SimulatedAnnealingContext ctx = SimulatedAnnealingContext.getInstance();
		ctx.setGenerator(g);
		ctx.setInitializator(i);
		ctx.setEvaluator(e);
		
		ctx.initTemperatureShedule();
		iterationsPerStage = 20000; // TODO: по хорошему должно зависеть от степеней свободы задачи
		
	}
	
	public GenerationList solve() {
		GenerationList currentGeneration = this.init.getInitialGeneration();
		SimulatedAnnealingContext ctx = SimulatedAnnealingContext.getInstance();
		
		while (!stoppingCriteria.isSatisfied(currentGeneration)) {
			GenerationList temp = currentGeneration;
			boolean nochange = true;
			for(int i = 0; i < iterationsPerStage; ++i) {
				GenerationList g = generator.getNext(currentGeneration);

				if (transitionCriteria.isSatisfied(currentGeneration, g)) {
					currentGeneration = g;
					nochange = false;
				}
			}
			if(nochange) {
				ctx.incCount();
			}
				
			else {
				ctx.countToZero();
			}
			System.out.println(ctx.getEvaluator().evaluate(currentGeneration.get(0)));
			
			ctx.getShedule().anneal();
		}

		return currentGeneration;
	}
}
