package simulatedannealing;

import core.Solution;

public interface ITransitionCriteria {
	public boolean isSatisfied(Solution a, Solution b);
}
