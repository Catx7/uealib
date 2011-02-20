package taboosearch.permutations;

// immutable
abstract public class InverseMove<S extends Solution> extends Move<S> {
	public InverseMove(int i, int j) {
		super(i, j);
	}
	
	@Override
	public S operateOn(S solution) {
		int[] permutation = solution.toArray();
		
		int startIdx = Math.min(i, j);
		int endIdx = Math.max(i, j);
		int n = endIdx - startIdx;

		for (int k = 0; k < n; ++k) {
			// swap [i_ + k] and [j_ - k]
			int tmp = permutation[startIdx + k];
			permutation[startIdx + k] = permutation[endIdx - k];
			permutation[endIdx - k] = tmp;
		}
		
		return makeSolution(permutation);
	}
	
	public String toString() {
		return "inverse( " + i + ", " + j + " )";
	}
}
