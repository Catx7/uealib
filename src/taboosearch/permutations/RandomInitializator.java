package taboosearch.permutations;

import java.util.Collections;
import java.util.LinkedList;

import taboosearch.Initializator;

public class RandomInitializator<S extends Solution> extends Initializator<S> {
	private AbstractSolutionFabric<S> solutionFabric;
	private int dimensionality;
	
	public RandomInitializator(AbstractSolutionFabric<S> solutionFabric, int dimensionality) {
		this.solutionFabric = solutionFabric;
		this.dimensionality = dimensionality;
	}
	
	@Override
	public S getInitialSolution(int seed) {
		LinkedList<Integer> numbers = new LinkedList<Integer>();
		
		for (int i = 0; i < dimensionality; ++i)
			numbers.add(i);
		Collections.shuffle(numbers);
		
		return solutionFabric.makeSolution(numbers);
	}
}
