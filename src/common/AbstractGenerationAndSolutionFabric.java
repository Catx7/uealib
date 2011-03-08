package common;

import core.Generation;
import core.Solution;

public interface AbstractGenerationAndSolutionFabric<S extends Solution, G extends Generation<S>> {
	public G makeGeneration();
	public S makeSolution();
}
