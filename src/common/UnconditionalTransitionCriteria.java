package common;

import core.Context;
import taboosearch.Generation;
import taboosearch.Solution;

public class UnconditionalTransitionCriteria<S extends Solution,
								G extends Generation<S>,
								C extends Context> implements core.TransitionCriteria<G> {

	public boolean isSatisfied(G g, G h) {
		return true;
	}
	
}
