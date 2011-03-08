package simulatedannealing.knapsack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.RandomAccess;

import readers.Collection;
import readers.items.Item;
import simulatedannealing.GenerationList;
import sun.reflect.generics.tree.Tree;
import core.Generator;

public class KnapsackGenerator implements Generator<GenerationList> {

	private Collection problem;
	private static <T> T choice(HashSet<T> hs) {
		Random r = new Random();
		int n = r.nextInt(hs.size());
		T result = null;
		int i=0;
		for(T tEntry: hs) {
			result = tEntry;
			if(i==n)
				break;
			++i;
		}
		return result;
	}
	private double getWeight(ItemSet is) {
		Item[] items = problem.getItems();
		HashSet<Integer>  used = is.getUsed();
		double sum = 0;
		for(Integer itemID: used) {
			sum += items[itemID].weight;
		}
		return sum;
	}
	public KnapsackGenerator(Collection problem)
	{
		this.problem = problem;
	}
	
	
	@Override
	public GenerationList getNext(GenerationList g) {

		ItemSet sol = (ItemSet)g.get(0);
		ItemSet result = new ItemSet((HashSet<Integer>)sol.getUsed().clone(), sol.getN());
		Item[] items = problem.getItems();
		HashSet<Integer> unused = result.getUnused();
		HashSet<Integer> used = result.getUsed();
		Integer toPut = choice(unused);
		double weight = getWeight(result);
		
		double delta = (problem.getConstrait()-weight); 
		while(delta <= items[toPut].weight) {
			Integer toRemove = choice(used);
			used.remove(toRemove);
			delta+=items[toRemove].weight;
			unused.add(toRemove);
		}
		unused.remove(toPut);
		used.add(toPut);
		
			
		
		GenerationList res = new GenerationList();
		res.add(result);
		return res;
	}

}
