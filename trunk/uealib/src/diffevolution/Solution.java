package diffevolution;

import diffevolution.Context;
import diffevolution.Solution;

public class Solution extends core.Solution{
	private int[] tour;
	private Double fitness;
	
	public Solution(int[] initialTour) {
		this.tour = initialTour.clone();
	}
		
	public Solution clone() {		
		return new Solution(this.tour.clone());
	}
	
	public int get(int index) {
		return this.tour[index];
	}
	public int[] getTour() {
		return this.tour.clone();
	}
	
	public int length() {
		return this.tour.length - 1;
	}
	
	public double getFitness() {
		if (this.fitness == null) {
			Context c = Context.getInstance();
			this.fitness = c.e.evaluate(this);
		}
		return this.fitness;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("[ " + this.tour[0]);
		
		for (int i = 1; i < tour.length; ++i)
			result.append(", " + this.tour[i]);

		result.append(" ] ");
		return result.toString();	
	}

	public int size() {
		return this.tour.length;
	}
	
	public static int[] leh2route(int[] leh, int n)
	{
	     boolean[] fl = new boolean[n];    // isIncludeTSP
	     int[] nat = new int[n];
	     int[]sol = new int[n];	// будущий маршрут, полученный из кода Лемера
	     for(int i = 0; i < n; ++i) {
	        fl[i] = false;
	        nat[i] = i;        
	     }
	     
	     for(int ind_gen = 0; ind_gen < n; ++ind_gen ){
	        int ind_nat = 0, num_ls = 0;
	        while ( num_ls < leh[ind_gen] + 1){
	           if ( !fl[ind_nat] ) ++num_ls;
	           ++ind_nat;
	        }
	        --ind_nat;
	        sol[ind_gen] = nat[ind_nat];
	        fl[ind_nat] = true;
	     }
	   return sol;
	}
	
	public static int[] route2leh(Solution sol)
	{
		int n = sol.size();
		int[] leh = new int[n];
		
	     for(int k = 0; k < n; ++k) {
	         int s = 0;
	         for(int j = k; j != n; ++j)
	             if ( sol.get(j) < sol.get(k))  ++s;
	         leh[k] = s;        
	     }
	     return leh;
	}
	

}
