package common;

import taboosearch.Solution;

public interface AbstractSolutionFabric<S extends Solution> {
	abstract public S make();
}
