package taboosearch.permutations;

import java.util.List;
import java.util.Vector;

import taboosearch.permutations.Attribute;
import taboosearch.Move;
import taboosearch.permutations.Solution;

abstract public class SwapMove<S extends Solution> implements Move<S>, SolutionFabric<S> {
	private int i, j;
	
	public SwapMove(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public int getI() {
		return this.i;
	}
	
	public int getJ() {
		return this.j;
	}
	
	abstract public SwapMove<S> clone();/* {
		return new SwapMove<S>(i, j);
	}*/
	
	public String toString() {
		return "swap( " + i + ", " + j + " )";
	}

	
	public S operateOn(S solution) {
 		int[] permutation = solution.toArray();
		
 		// swap i and j
		int tmp = permutation[i];
		permutation[i] = permutation[j];
		permutation[j] = tmp;
		
		return makeSolution(permutation);
	}

	@Override
	public List<Attribute<S>> getAttributes(S solution) {
		int v1 = solution.get(i);
		int v2 = solution.get(j);
		
		List<Attribute<S>> result = new Vector<Attribute<S>>();
		result.add(new Attribute<S>(v1));
		result.add(new Attribute<S>(v2));
		return result;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + j;
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SwapMove<S> other = (SwapMove<S>) obj;
		if (i != other.i)
			return false;
		if (j != other.j)
			return false;
		return true;
	}
}
