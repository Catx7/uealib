package genetic;

public class GenerationFabric<S extends Solution<S>>
		implements
		AbstractGenerationFabric<Generation<S>, GeneticContext<Generation<S>, S>> {

	@Override
	public Generation<S> makeGeneration(GeneticContext<Generation<S>, S> context) {
		return new Generation<S>(context);
	}

}
