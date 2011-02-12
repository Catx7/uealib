package taboosearch.permutations;

import java.util.List;

public class Solution extends taboosearch.Solution {
	final protected int[] permutation;
	protected double cost;
	
	public Solution(int[] route) {
		this.permutation = route.clone();
	}
	
	public Solution(List<Integer> route) {
		this.permutation = new int[route.size()];
		for (int i = 0; i < this.permutation.length; ++i)
			this.permutation[i] = route.get(i);
	}
	
	public int length() {
        return this.permutation.length;
	}
	
	public int get(int index) {
		return this.permutation[index];
	}
	
	public int[] toArray() {
		return this.permutation.clone();
	}

	@Override
	public String getStringRepresentation() {
		StringBuilder result = new StringBuilder();
		result.append("[ ");
		
		result.append(permutation[0] + 1);
		for (int i = 1; i < permutation.length; ++i) {
			result.append(", ");
			result.append(permutation[i] + 1);
		}

		result.append(" ] ");
		return result.toString();
	}

	@Override
	public Solution copy() {
		return new Solution(permutation);
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getCost() {
		return cost;
	}
}
