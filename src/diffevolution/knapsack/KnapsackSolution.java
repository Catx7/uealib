package diffevolution.knapsack;

import java.util.List;


public class KnapsackSolution extends diffevolution.ArraySolution<Integer> {
	
	final protected int[] collection;	
	public static int[] maxItemNum;
	
	
	public KnapsackSolution(int[] items) {
		this.collection = items.clone();
	}
	
	public KnapsackSolution(List<Integer> items) {
		this.collection = new int[items.size()];
		for (int i = 0; i < this.collection.length; ++i)
			this.collection[i] = items.get(i);
	}
	
	public int length() {
        return this.collection.length;
	}
	
	public Integer get(int index) {
		return this.collection[index];
	}
	
	public void set(int index, Integer value) {
		this.collection[index] = value;
	}
	
	public int[] toArray() {
		return this.collection.clone();
	}

	public String getRepresentation() {
		StringBuilder result = new StringBuilder();
		result.append("[ ");
		
		result.append(collection[0]);
		for (int i = 1; i < collection.length; ++i) {
			result.append(", ");
			result.append(collection[i]);
		}

		result.append(" ] ");
		return result.toString();
	}
	
	@Override
	public KnapsackSolution copy() {
		return new KnapsackSolution(collection);
	}
	

}
