package taboosearch.permutations;

//immutable
abstract public class SwapMove<S extends Solution> extends Move<S> {
	public SwapMove(int i, int j) {
		super(i, j);
	}
	
	@Override
	public S operateOn(S solution) {
 		int[] permutation = solution.toArray();
		
 		// swap i and j
		int tmp = permutation[i];
		permutation[i] = permutation[j];
		permutation[j] = tmp;
		
		return makeSolution(permutation);
	}
	
	public String toString() {
		return "swap( " + i + ", " + j + " )";
	}
}
