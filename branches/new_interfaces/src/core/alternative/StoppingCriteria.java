package core.alternative;

import core.Solution;

public interface StoppingCriteria<S extends Solution> {
	public boolean isSatisfied(S solution);
}
