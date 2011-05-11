package simulatedannealing.knapsack;

import java.util.HashSet;

import readers.KnapsackTask;
import readers.items.Item;
import simulatedannealing.IEvaluator;
import core.Solution;

public class KnapsackEvaluator implements IEvaluator<ItemSet> {

	private KnapsackTask problem;

	public KnapsackEvaluator(KnapsackTask problem) {
		this.problem = problem;
	}

	@Override
	public int compare(ItemSet arg0, ItemSet arg1) {
		double fit1 = evaluate(arg0);
		double fit2 = evaluate(arg1);
		if (Math.abs(fit1 - fit2) < 1e-10)
			return 0;

		if (fit1 < fit2)
			return -1;
		return 1;
	}

	@Override
	public double evaluate(ItemSet itemset) {
		
		if(itemset.getFitness() != null)
			return itemset.getFitness();
		
		
		double sum = 0;
		HashSet<Integer> used = itemset.getUsed();
		Item[] items = problem.getItems();
		for (Integer itemID : used) {
			sum += items[itemID].getUtility();
		}
		
		itemset.setFitness(new Double(sum));
		return sum;
	}

}
