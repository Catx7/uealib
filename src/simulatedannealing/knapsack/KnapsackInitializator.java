package simulatedannealing.knapsack;

import java.util.HashSet;

import readers.KnapsackTask;
import readers.items.Item;
import simulatedannealing.IInitializator;
import core.Initializator;
import core.Solution;

public class KnapsackInitializator implements IInitializator<ItemSet> {

	private KnapsackTask problem;

	public KnapsackInitializator(KnapsackTask problem) {
		this.problem = problem;
	}

	@Override
	public ItemSet getInitialSolution() {
		HashSet<Integer> used = new HashSet<Integer>();

		Item[] items = problem.getItems();
		int weight = 0;
		int i = 0;
		while (i < items.length && weight <= problem.getCapacity()) {
			if (items[i].getWeight() <= (problem.getCapacity() - weight)) {
				used.add(i);
				weight += items[i].getWeight();
			}
			++i;
		}

		ItemSet sol = new ItemSet(used, problem.getItemsNumber());
		return sol;

	}

}
