package core.alternative;

import core.Solution;

public interface Initializator<S extends Solution> {
	public S getInitialSolution();
}
