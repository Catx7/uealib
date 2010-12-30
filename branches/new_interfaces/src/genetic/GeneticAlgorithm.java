package genetic;

public class GeneticAlgorithm<G extends genetic.Generation<S>, S extends genetic.Solution<S>>
		extends core.Algorithm<G> {

	protected GeneticContext<G, S> context;

	public GeneticAlgorithm(Initializator<G, S> i, core.Generator<G> g,
			core.StoppingCriteria<G> stop, core.Selector<G> selector,
			core.TransitionCriteria<G> trans, GeneticContext<G, S> context) {
		super(i, g, stop, selector, trans, context);
	}
}