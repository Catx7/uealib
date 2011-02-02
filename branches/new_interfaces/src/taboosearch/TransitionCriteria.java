package taboosearch;

public class TransitionCriteria<S extends Solution,
								G extends Generation<S>,
								C extends Context<S, G>> implements core.TransitionCriteria<G> {

	public boolean isSatisfied(G g, G h) {
		return true;
	}
	
}
