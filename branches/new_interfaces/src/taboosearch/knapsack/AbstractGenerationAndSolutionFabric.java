package taboosearch.knapsack;

import java.util.HashSet;
import taboosearch.Generation;

public abstract class AbstractGenerationAndSolutionFabric
			implements common.AbstractGenerationAndSolutionFabric<KPSolution, Generation<KPSolution>> {
	abstract public Generation<KPSolution> makeGeneration();
	
	public Generation<KPSolution> makeGeneration(KPSolution solution) {
		Generation<KPSolution> generation = makeGeneration();
		generation.add(solution);
		return generation;
	}
	
	abstract public KPSolution makeSolution(HashSet<Integer> items);
	
	public KPSolution makeSolution() {
		return makeSolution(new HashSet<Integer>());
	}
}
