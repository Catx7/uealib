package diffevolution.tsp;

import java.util.LinkedList;
import java.util.List;

public class TSPSolution extends diffevolution.ArraySolution<Integer> { 
	
	final protected int[] route;
	
		
	public TSPSolution(int[] route) {
		this.route = route.clone();
	}
	
	public TSPSolution(List<Integer> route) {
		this.route = new int[route.size()];
		for (int i = 0; i < this.route.length; ++i)
			this.route[i] = route.get(i);
	}
	
	public int length() {
        return this.route.length;
	}
	
	public Integer get(int index) {
		return this.route[index];
	}
	
	public int[] toArray() {
		return this.route.clone();
	}

	@Override
	public String getRepresentation() {
		StringBuilder result = new StringBuilder();
		result.append("[ ");
		
		result.append(route[0] + 1);
		for (int i = 1; i < route.length; ++i) {
			result.append(", ");
			result.append(route[i] + 1);
		}

		result.append(" ] ");
		return result.toString();
	}

	@Override
	public TSPSolution copy() {
		return new TSPSolution(route);
	}
	
	
	public TSPSolution leh2route() {
		int n = this.route.length;
		LinkedList<Integer> solution = new LinkedList<Integer>();	
	    
	    int[] natural = new int[n];
	    for(int i = 0; i < n; ++i) {
	       natural[i] = i;        
	    }
	    
	    for(int inx = 0; inx < n; ++inx ){
	       int inxNat = 0, numLess = 0;
	       while ( numLess < this.route[inx] + 1){
	          if ( !solution.contains(natural[inxNat]) )
	             ++numLess;
	          ++inxNat;
	       }
	       solution.add(natural[--inxNat]);
	    }
	    return new TSPSolution(solution);
	}
	
	public TSPSolution route2leh()	{
		int n = this.route.length;
		int[] leh = new int[n];
		
	    for(int k = 0; k < n; ++k) {
	         int s = 0;
	         for(int j = k; j != n; ++j)
	             if ( this.route[j] < this.route[k])  
	            	 ++s;
	         leh[k] = s;        
	     }
	     return new TSPSolution(leh);
	}

	@Override
	public void set(int index, Integer value) {
		this.route[index] = value;
	}
	
}
