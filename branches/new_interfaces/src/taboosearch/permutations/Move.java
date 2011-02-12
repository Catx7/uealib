package taboosearch.permutations;

import java.util.List;
import java.util.Vector;


public abstract class Move<S extends Solution> implements taboosearch.Move<S>, SolutionFabric<S> {
	protected int i, j;
	
	public Move(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public int getI() {
		return this.i;
	}
	
	public int getJ() {
		return this.j;
	}
	
	abstract public Move<S> clone();
	
	public String toString() {
		return "swap( " + i + ", " + j + " )";
	}

	
	public abstract S operateOn(S solution);

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
		Move<S> other = (Move<S>) obj;
		if (i != other.i)
			return false;
		if (j != other.j)
			return false;
		return true;
	}
}
