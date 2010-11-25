package simulatedannealing;

import core.Generation;
import core.Generator;
import core.Initializator;

public class SimulatedAnnealingAlgorithm extends core.Algorithm {
	
	private int iterationsPerStage;
	public SimulatedAnnealingAlgorithm(Initializator i, Generator g) {
		super(i, g, new StabilyzedStoppingCriteria(20), new DummySelector(), new MetropolisRule());
		iterationsPerStage = 20000; // TODO: по хорошему должно зависеть от степеней свободы задачи
		
	}
	
	@SuppressWarnings("unchecked")
	public Generation solve() {
		Generation currentGeneration = this.init.getInitialGeneration();
		SimulatedAnnealingContext ctx = SimulatedAnnealingContext.getInstance();
		
		while (!stoppingCriteria.isSatisfied(currentGeneration)) {
			for(int i = 0; i < iterationsPerStage; ++i) {
				Generation g = generator.getNext(currentGeneration);

				if (transitionCriteria.isSatisfied(currentGeneration, g)) {
					currentGeneration = g;
				}
			}
			
			
			ctx.getShedule().anneal();
		}

		return currentGeneration;
	}
}
