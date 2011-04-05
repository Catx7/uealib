package diffevolution.knapsack;

import readers.KnapsackTask;
import diffevolution.Evaluator;
import readers.items.Item;

public class KnapsackEvaluator extends Evaluator<KnapsackSolution> {

	private Item[] items;
	private int n;
	private double V;
	
	public KnapsackEvaluator(KnapsackTask c) {
		this.items = c.getItems();
		this.n = c.getItemsNumber();
		this.V = c.getCapacity();
	}
	
	public double evaluate(KnapsackSolution solution) {
		assert n == solution.length();
		
		double value = 0.0;
		double weights = 0.0;
		for ( int i = 0; i < n; ++i ) {
			value += solution.get(i)*items[i].getUtility();
			weights += solution.get(i)*items[i].getWeight();
		}
		value = -value;
		if (weights > this.V)	// the total weight is more than limit
			value = Double.MAX_VALUE;
		
		return value;
	}
	
	public double getFitness(KnapsackSolution solution) {
		return -evaluate(solution);
	}
}