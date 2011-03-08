package taboosearch.permutations;

import java.util.List;

import taboosearch.Generation;
import taboosearch.Solution;

public abstract class AbstractGenerationAndSolutionFabric<S extends Solution, G extends Generation<S>>
			implements common.AbstractGenerationAndSolutionFabric<S, G> {
	/* Возвращает пустое поколение. */
	abstract public G makeGeneration();
	
	/* Возвращает поколение, содержащее решение solution. */
	public G makeGeneration(S solution) {
		G generation = makeGeneration();
		generation.add(solution);
		return generation;
	}
	
	/* Возвращает решение, инициализированное перестановкой permutation. */
	abstract public S makeSolution(int[] permutation);
	
	/* Возвращает решение, инициализированное перестановкой list. */
	public S makeSolution(List<Integer> list) {
		int[] permutation = new int[list.size()];
		for (int i = 0; i < permutation.length; ++i)
			permutation[i] = list.get(i);
		return makeSolution(permutation);
	}
}
