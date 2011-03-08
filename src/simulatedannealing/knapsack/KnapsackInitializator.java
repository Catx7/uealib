package simulatedannealing.knapsack;

import java.util.HashSet;

import readers.Collection;
import readers.items.Item;
import simulatedannealing.GenerationList;
import core.Initializator;
import core.Solution;

public class KnapsackInitializator implements Initializator<GenerationList> {

	private Collection problem;
	public KnapsackInitializator(Collection problem) {
		this.problem = problem;
	}
	@Override
	public GenerationList getInitialGeneration() {
		int N = problem.getItemsNumber();
		HashSet<Integer> used = new HashSet<Integer>();
		
		Item[] items = problem.getItems();
		int weight = 0;
		int i=0;
		while(i<items.length && weight<=problem.getConstrait()) {
			if(items[i].weight <= (problem.getConstrait()-weight))
			{
				used.add(i);
				weight += items[i].weight;
			}
			++i;
		}
		
		Solution sol = new ItemSet(used, problem.getItemsNumber());
		GenerationList res = new GenerationList();
		res.add(sol);
		return res;
		
	}
	
}
