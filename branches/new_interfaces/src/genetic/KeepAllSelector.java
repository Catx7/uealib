package genetic;

public class KeepAllSelector<G extends genetic.Generation<? extends genetic.Solution<?>>>
		implements core.Selector<G> {

	@Override
	public G keepTheBestSolutions(G g, G currentGeneration) {
		return g;
	}
}
