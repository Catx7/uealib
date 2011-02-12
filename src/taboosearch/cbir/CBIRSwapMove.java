package taboosearch.cbir;

import java.util.List;
import java.util.Vector;

import taboosearch.Move;

// immutable
public class CBIRSwapMove implements Move<CBIRSolution> {	
	private int i, j;
	
	public CBIRSwapMove(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public int getI() {
		return this.i;
	}
	
	public int getJ() {
		return this.j;
	}
	
	public CBIRSwapMove clone() {
		return new CBIRSwapMove(i, j);
	}
	
	public String toString() {
		return "swap( " + i + ", " + j + " )";
	}

	@Override
	public CBIRSolution operateOn(CBIRSolution solution) {
 		int[] route = solution.toArray();
		
		int tmp = route[i];
		route[i] = route[j];
		route[j] = tmp;
		
		return new CBIRSolution(route);
	}

	@Override
	public List<CBIRAttribute> getAttributes(CBIRSolution solution) {
		int v1 = solution.get(i);
		int v2 = solution.get(j);
		
		List<CBIRAttribute> result = new Vector<CBIRAttribute>();
		result.add(new CBIRAttribute(v1));
		result.add(new CBIRAttribute(v2));
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CBIRSwapMove other = (CBIRSwapMove) obj;
		if (i != other.i)
			return false;
		if (j != other.j)
			return false;
		return true;
	}

}
