package diffevolution.tsp;

import java.util.List;

public class TSPSolution extends diffevolution.Solution<TSPSolution> { 
	
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
	
	public int get(int index) {
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
	
	
	@Override
	public void doCrossover(TSPSolution donor) {
		double Cr = 0.8;
        for(int i = 0; i < donor.length(); ++i){
		    double p = Math.random();
		    if (p > Cr) 
		 	   this.route[i] = donor.get(i);
		 }
	}

	public TSPSolution leh2route() {
		int n = this.route.length;
	    boolean[] fl = new boolean[n];    
	    int[] nat = new int[n];
	    int[] sol = new int[n];	
	    for(int i = 0; i < n; ++i) {
	       fl[i] = false;
	       nat[i] = i;        
	    }
	    
	    for(int ind_gen = 0; ind_gen < n; ++ind_gen ){
	       int ind_nat = 0, num_ls = 0;
	       while ( num_ls < this.route[ind_gen] + 1){
	          if ( !fl[ind_nat] )
	             ++num_ls;
	          ++ind_nat;
	       }
	       --ind_nat;
	       sol[ind_gen] = nat[ind_nat];
	       fl[ind_nat] = true;
	    }
	    return new TSPSolution(sol);
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
	
}
