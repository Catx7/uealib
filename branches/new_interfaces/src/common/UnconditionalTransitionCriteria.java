package common;

import core.Context;
import core.Generation;
import core.Solution;

public class UnconditionalTransitionCriteria<S extends Solution,
								G extends Generation<S>,
								C extends Context> implements core.TransitionCriteria<G> {

	public boolean isSatisfied(G g, G h) {
		return true;
	}
	
}
