package simulatedannealing.knapsack;

import java.util.HashSet;

import readers.KnapsackTask;
import readers.items.Item;
import simulatedannealing.GenerationList;
import core.Initializator;
import core.Solution;

public class KnapsackInitializator implements Initializator<GenerationList> {

	private KnapsackTask problem;

	public KnapsackInitializator(KnapsackTask problem) {
		this.problem = problem;
	}

	@Override
	public GenerationList getInitialGeneration() {
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

		Solution sol = new ItemSet(used, problem.getItemsNumber());
		GenerationList res = new GenerationList();
		res.add(sol);
		return res;

	}

}
