package taboosearch.permutations;

import java.util.List;

import taboosearch.Generation;
import taboosearch.Solution;

public abstract class AbstractGenerationAndSolutionFabric<S extends Solution, G extends Generation<S>> {
	abstract public G makeGeneration();
	abstract public S makeSolution(int[] permutation);
	abstract public S makeSolution(List<Integer> permutation);
}
