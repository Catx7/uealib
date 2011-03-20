package simulatedannealing;

import core.Algorithm;
import core.Generator;
import core.Initializator;

public class SimulatedAnnealingAlgorithm extends Algorithm<GenerationList> {

	private int iterationsPerStage;
	private SimulatedAnnealingContext ctx;

	public SimulatedAnnealingAlgorithm(Evaluator e,
			Initializator<GenerationList> i, Generator<GenerationList> g) {
		super(i, g, null, null, null, null);
		ctx = new SimulatedAnnealingContext();

		this.transitionCriteria = new MetropolisRule(ctx);
		this.stoppingCriteria = new StabilyzedStoppingCriteria(20, ctx);

		ctx.setGenerator(g);
		ctx.setInitializator(i);
		ctx.setEvaluator(e);

		ctx.initTemperatureShedule();
		iterationsPerStage = 800; // TODO: по хорошему должно зависеть от
		// степеней свободы задачи

	}

	public void setIterationsPerStage(int iterationsPerStage) {
		this.iterationsPerStage = iterationsPerStage;
	}

	public GenerationList solve() {
		GenerationList currentGeneration = this.init.getInitialGeneration();

		while (!stoppingCriteria.isSatisfied(currentGeneration)) {
			boolean nochange = true;
			for (int i = 0; i < iterationsPerStage; ++i) {
				GenerationList g = generator.getNext(currentGeneration);

				if (transitionCriteria.isSatisfied(currentGeneration, g)) {
					currentGeneration = g;
					nochange = false;
				}
			}
			if (nochange) {
				ctx.incCount();
			}

			else {
				ctx.countToZero();
			}
			System.out.println(ctx.getEvaluator().evaluate(
					currentGeneration.get(0)));

			ctx.getShedule().anneal();
		}

		return currentGeneration;
	}
}
