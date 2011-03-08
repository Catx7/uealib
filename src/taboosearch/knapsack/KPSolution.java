package taboosearch.knapsack;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class KPSolution extends taboosearch.Solution implements Iterable<Integer> {
	final protected HashSet<Integer> items;
	
	public KPSolution(HashSet<Integer> items) {
		this.items = new HashSet<Integer>(items);
	}
	
	public boolean contains(Integer item) {
		return items.contains(item);
	}
		
	public int size() {
        return items.size();
	}

	@Override
	public String getStringRepresentation() {
		StringBuilder result = new StringBuilder();
		
		List<Integer> itemsList = new LinkedList<Integer>();
		for (Integer i : items) {	
			itemsList.add(i);
		}
		Collections.sort(itemsList);
		
		result.append("[ ");
		for (Integer i : itemsList) {	
			result.append(i);
			result.append(" ");
		}
		result.append("] ");
		return result.toString();
	}

	@Override
	public core.Solution copy() {
		return new KPSolution(items);
	}

	@Override
	public Iterator<Integer> iterator() {
		return items.iterator();
	}
}
