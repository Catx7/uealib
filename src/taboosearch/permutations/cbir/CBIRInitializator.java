package taboosearch.permutations.cbir;

import java.util.Collections;
import java.util.LinkedList;

import common.AbstractGenerationFabric;

import taboosearch.Initializator;
import taboosearch.readers.FeaturesSpace;

public class CBIRInitializator extends Initializator<CBIRSolution> {
	private int n = 0;
	CBIREvaluator evaluator;
	
	public CBIRInitializator(FeaturesSpace space, CBIREvaluator evaluator) {	
		this.n = space.n;
		this.evaluator = evaluator;
	}

	public CBIRSolution getInitialSolution(int seed) {
		LinkedList<Integer> numbers = new LinkedList<Integer>();

		for (int i = 0; i < n; ++i)
			numbers.add(i);
		Collections.shuffle(numbers);
		
		int[] order = new int[numbers.size()];
		for (int i = 0; i < numbers.size(); ++i)
			order[i] = numbers.get(i);

		CBIRSolution solution = new CBIRSolution(order);
		solution.setCost(evaluator.evaluate(solution));
		return solution;
	}

}
