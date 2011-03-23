package genetic.knapsack;

import readers.items.Item;

public class KnapsackEvaluator extends genetic.Evaluator<KnapsackItems> {

	private Item[] collectionItems;
	private double constraint;

	public KnapsackEvaluator(readers.Collection collection) {
		this.collectionItems = collection.getItems();
		this.constraint = collection.getConstrait();
	}

	@Override
	public int compare(KnapsackItems o1, KnapsackItems o2) {
		double o1fitness = evaluate(o1);
		double o2fitness = evaluate(o2);
		if (Math.abs(o1fitness - o2fitness) < 0.0000001)
			return 0;
		if (o1fitness > o2fitness)
			return 1;
		return -1;
	}

	@Override
	public double evaluate(KnapsackItems items) {
		return getTotalWeight(items);
	}
	
	private double getTotalWeight(KnapsackItems items) {
		double totalWeight = 0;
		for (int i = 0; i < items.getLength(); ++i)
			if (items.isInKnapsack(i))
				totalWeight += collectionItems[i].weight;
		return totalWeight;
	}
	
	private double getConstrainedWeight(KnapsackItems items) {
		double weight = 0;
		for (int i = 0; i < items.getLength() && weight <= constraint; ++i)
			if (items.isInKnapsack(i))
				weight += collectionItems[i].weight;
		return weight;
	}

	public boolean isValid(KnapsackItems items) {
		return getConstrainedWeight(items) <= constraint;
	}
	
	@Override
	public void makeFeasibleSolution(KnapsackItems items) {
		double weight = getTotalWeight(items);
		while (weight > constraint)
			weight -= collectionItems[items.dropRandomItem()].weight;
	}

}
