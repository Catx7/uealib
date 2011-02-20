package taboosearch.cbir;

import java.util.Collections;
import java.util.LinkedList;

import taboosearch.Initializator;
import taboosearch.readers.FeaturesSpace;

public class CBIRInitializator extends Initializator<CBIRSolution, CBIRGeneration> {
	private int n = 0;
	CBIREvaluator evaluator;
	
	public CBIRInitializator(FeaturesSpace space, CBIREvaluator evaluator) {	
		this.n = space.n;
		this.evaluator = evaluator;
	}

	public CBIRGeneration getInitialGeneration() {
		LinkedList<Integer> numbers = new LinkedList<Integer>();

		for (int i = 0; i < n; ++i)
			numbers.add(i);
		Collections.shuffle(numbers);
		
		int[] order = new int[numbers.size()];
		for (int i = 0; i < numbers.size(); ++i)
			order[i] = numbers.get(i);
		
		CBIRGeneration result = new CBIRGeneration();
		CBIRSolution solution = new CBIRSolution(order);
		solution.setCost(evaluator.evaluate(solution));
		result.add(solution);
		return result;
	}

	@Override
	public CBIRGeneration getInitialGeneration(int size) {
		// TODO Auto-generated method stub
		return null;
	}
}
