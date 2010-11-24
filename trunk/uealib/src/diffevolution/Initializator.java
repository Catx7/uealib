package diffevolution;

import diffevolution.Solution;

public class Initializator implements core.Initializator {
	
	private double[][] weights = null;
	private int n = 0;
	
	public Initializator(double[][] weights) {
		assert weights.length == weights[0].length;
		
		this.weights = weights;
		this.n = weights.length;
	}
	
	
	private Solution getInitSolution(int num_node) {
		int[] result = new int[n];
	    boolean[] fl = new boolean[n];
	    for(int i = 0; i < fl.length; ++i)
	        fl[i] = false;
	    int ind = 1;
	    if (num_node == 0) ind = 0;
	    int t = ind;
	    result[ind] = num_node;
	    fl[num_node] = true;
	    int k = num_node;
	     
	    int i = ind;
	    while (i < n-1) {
	         double nearest = 1000;
	         for ( int j = 1; j < n; ++j) {      
	             if ( fl[j] == false && k != j) {
	                 if ( weights[k][j] < nearest ) {
	                     nearest = weights[k][j];
	                     k = j;     
	                 }  
	             }
	         }
	         fl[k] = true;
	         result[++t] = k;
	         ++i;
	     }
		return new Solution(result);
	}
	public Generation getInitialGeneration() {
		Generation g = new Generation();
		Context c = new Context();
			
		if (n < Context.Gn) {
			int k = 0;
			while(k < Context.Gn) 
				g.add(this.getInitSolution((k++)%n));
		}
		else
			for(int i = 0; i < Context.Gn; ++i)
				g.add(this.getInitSolution(i));
			
		return g;
	}
	
}