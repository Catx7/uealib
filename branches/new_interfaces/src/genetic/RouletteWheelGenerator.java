package genetic;

import core.Generator;

public class RouletteWheelGenerator<G extends genetic.Generation<S>, S extends genetic.Solution<S>>
		implements Generator<G> {

	private static java.util.Random random = new java.util.Random();

	protected boolean useElitism;

	protected double defaultMutationProbability = .05;

	protected double mutationProbability = defaultMutationProbability;

	protected double totalFitness;

	protected GeneticContext<G, S> context;

	protected boolean[] selected;

	protected S selectElem(G g) {
		if (selected == null)
			selected = new boolean[g.size()];
		double limit = random.nextDouble() * totalFitness, sum = 0;
		int i;
		for (i = 0; i < g.size(); ++i) {
			sum += context.getEvaluator().evaluate(g.get(i));
			if (sum >= limit && !selected[i]) {
				selected[i] = true;
				return g.get(i);
			}
		}
		return g.get(0);
	}

	@Override
	public G getNext(G g) { // not classical
		if (context.getEvaluator().evaluate(g.getBest().get(0)) > context
				.getBestFitness()) {
			context.setBestKnownRoute(g.getBest().get(0));
		}
		totalFitness = 0;
		for (S solution : g)
			totalFitness += context.getEvaluator().evaluate(solution);
		G newG = context.getGenerationFabric().makeGeneration(context);
		int i;
		for (i = 0; i < g.size() - 2; i += 2) {
			newG.add(selectElem(g).copy());
			newG.add(selectElem(g).copy());
			newG.get(i).doCrossover(newG.get(i + 1));
		}
		newG.add(selectElem(g).copy());
		if (i == g.size() - 2) { // even
			newG.add(selectElem(g).copy());
			newG.get(i).doCrossover(newG.get(i + 1));
		}
		for (S solution : newG)
			if (random.nextDouble() < mutationProbability)
				solution.mutate();
		if (useElitism) {
			newG.set(random.nextInt(g.size()), context.getBestKnownRoute()
					.copy());
		}
		selected = null;
		context.incGenerationNumber();
		int genNum = context.getCurrentGenerationNumber();
		if (0 == genNum % 100)
			System.err.println(genNum);
		return newG;
	}

	public RouletteWheelGenerator(GeneticContext<G, S> context) {
		this.context = context;
	}

	public RouletteWheelGenerator(GeneticContext<G, S> context,
			boolean useElitism) {
		this.useElitism = useElitism;
		this.context = context;
	}

	public RouletteWheelGenerator(GeneticContext<G, S> context,
			boolean useElitism, double mutationProbability) {
		this.useElitism = useElitism;
		this.mutationProbability = mutationProbability;
	}

}
