package genetic;

public abstract class Initializator<G extends genetic.Generation<S>, S extends genetic.Solution<S>>
		implements core.Initializator<G> {

	protected GeneticContext<G, S> context;
	protected G generationInstance;

	public G getInitialGeneration(RandomSolutionGenerator<S> generator,
			int generationSize) {
		for (int i = 0; i < generationSize; ++i)
			generationInstance.add(generator.generateRandomSolution());
		return generationInstance;
	}

	@Override
	abstract public G getInitialGeneration();

	public Initializator(GeneticContext<G, S> ctx, G instance) {
		this.context = ctx;
		this.generationInstance = instance;
	}

}
