package taboosearch.permutations;

public class Solution extends taboosearch.Solution {
	final protected int[] permutation;
	protected Double cost;
	
	public Solution(int[] permutation) {
		this.permutation = permutation.clone();
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

}
