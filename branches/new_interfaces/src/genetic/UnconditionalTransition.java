package genetic;

public class UnconditionalTransition<G extends genetic.Generation<? extends genetic.Solution<?>>>
		implements core.TransitionCriteria<G> {

	@Override
	public boolean isSatisfied(G g, G h) {
		return true;
	}

}
