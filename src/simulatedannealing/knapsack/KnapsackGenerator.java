package simulatedannealing.knapsack;

import java.util.HashSet;
import java.util.Random;

import readers.KnapsackTask;
import readers.items.Item;
import simulatedannealing.IGenerator;
import core.Generator;

public class KnapsackGenerator implements IGenerator<ItemSet> {

	private KnapsackTask problem;

	private static <T> T choice(HashSet<T> hs) {
		Random r = new Random();
		int n = r.nextInt(hs.size());
		T result = null;
		int i = 0;
		for (T tEntry : hs) {
			result = tEntry;
			if (i == n)
				break;
			++i;
		}
		return result;
	}

	private double getWeight(ItemSet is) {
		Item[] items = problem.getItems();
		HashSet<Integer> used = is.getUsed();
		double sum = 0;
		for (Integer itemID : used) {
			sum += items[itemID].getWeight();
		}
		return sum;
	}

	public KnapsackGenerator(KnapsackTask problem) {
		this.problem = problem;
	}

	@Override
	public ItemSet getNext(ItemSet sol) {

		ItemSet result = new ItemSet((HashSet<Integer>) sol.getUsed().clone(),
				sol.getN());
		Item[] items = problem.getItems();
		HashSet<Integer> unused = result.getUnused();
		HashSet<Integer> used = result.getUsed();
		Integer toPut = choice(unused);
		double weight = getWeight(result);

		double delta = (problem.getCapacity() - weight);
		while (delta <= items[toPut].getWeight()) {
			Integer toRemove = choice(used);
			used.remove(toRemove);
			delta += items[toRemove].getWeight();
			unused.add(toRemove);
		}
		unused.remove(toPut);
		used.add(toPut);

		return result;
	}

}
