package simulatedannealing.knapsack;

import java.util.HashSet;

import readers.Collection;
import readers.items.Item;
import simulatedannealing.Evaluator;
import core.Solution;

public class KnapsackEvaluator implements Evaluator {

	private Collection problem;

	public KnapsackEvaluator(Collection problem) {
		this.problem = problem;
	}

	@Override
	public int compare(Solution arg0, Solution arg1) {
		double fit1 = evaluate(arg0);
		double fit2 = evaluate(arg1);
		if (Math.abs(fit1 - fit2) < 1e-10)
			return 0;

		if (fit1 < fit2)
			return -1;
		return 1;
	}

	@Override
	public double evaluate(Solution s) {
		ItemSet itemset = (ItemSet) s;
		double sum = 0;
		HashSet<Integer> used = itemset.getUsed();
		Item[] items = problem.getItems();
		for (Integer itemID : used) {
			sum += items[itemID].utility;
		}
		return sum;
	}

}
