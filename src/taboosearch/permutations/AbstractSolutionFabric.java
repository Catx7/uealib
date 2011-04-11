package taboosearch.permutations;

import java.util.List;

public abstract class AbstractSolutionFabric<S extends Solution> {
	abstract public S makeSolution(int[] permutation);
	
	public S makeSolution(List<Integer> list) {
		int[] permutation = new int[list.size()];
		for (int i = 0; i < permutation.length; ++i)
			permutation[i] = list.get(i);
		return makeSolution(permutation);
	}
}
