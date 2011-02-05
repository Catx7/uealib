package taboosearch.tsp;

import java.util.List;
import java.util.Vector;

import taboosearch.Move;

// immutable
public class TSPSwapMove implements Move<TSPSolution> {	
	private int i, j;
	
	public TSPSwapMove(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public int getI() {
		return this.i;
	}
	
	public int getJ() {
		return this.j;
	}
	
	public TSPSwapMove clone() {
		return new TSPSwapMove(i, j);
	}
	
	public String toString() {
		return "( " + i + ", " + j + " )";
	}

	@Override
	public TSPSolution operateOn(TSPSolution solution) {
		int[] route = solution.toArray();
		
		int tmp = route[i];
		route[i] = route[j];
		route[j] = tmp;
		
		return new TSPSolution(route);
	}

	@Override
	public List<TSPAttribute> getAttributes(TSPSolution solution) {
		int v1 = solution.get(i);
		int v2 = solution.get(j);
		
		List<TSPAttribute> result = new Vector<TSPAttribute>();
		result.add(new TSPAttribute(v1));
		result.add(new TSPAttribute(v2));
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
		TSPSwapMove other = (TSPSwapMove) obj;
		if (i != other.i)
			return false;
		if (j != other.j)
			return false;
		return true;
	}
}
