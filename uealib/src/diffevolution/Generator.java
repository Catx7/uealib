package diffevolution;

import diffevolution.Generation;
import diffevolution.Solution;
import diffevolution.Context;

import java.util.Random;


public class Generator implements core.Generator{

	private int[] mutation(Solution leh_1, Solution leh_2, Solution leh_3, int n )
	{
		int[] result = new int[n];
	    for(int i = 0; i < n; ++i)
	        result[i] = (int) Math.abs((leh_1.get(i) + Context.F*(leh_2.get(i) - leh_3.get(i)) % (n - i)) % (n - i));
	   // for(int i = 0; i < n; ++i)
	    //System.out.print(result[i]);
	     return result;
	}
	
	public Generation getNext(core.Generation g) {
		Generation newGen = new Generation();
		
		for(int k = 0; k < Context.Gn; ++k) {
			
		    int x=1, y=1, z=1;
		    Random rand = new Random();
	
		    while(x == y || y == z ){
		        x = rand.nextInt(Context.Gn - 1);
		    	y = rand.nextInt(Context.Gn - 1);
		    	z = rand.nextInt(Context.Gn - 1);
		    }
		    int n = (((Generation) g).get(0)).size();
		  
		  
		    Solution leh_mut = new Solution(mutation(new Solution(Solution.route2leh(((Generation) g).get(x))), new Solution(Solution.route2leh(((Generation) g).get(y))),new Solution(Solution.route2leh(((Generation) g).get(z))), n));
		    Solution leh_curr = new Solution (Solution.route2leh(((Generation) g).get(k)));	 // после Crossover
		    
		    int[] cross = new int[n];
		    for(int i = 0; i < n; ++i){
		       double p = Math.random();
		       if (p <= Context.Cr) 
		    	   cross[i] = leh_curr.get(i);
		       else
		    	   cross[i] = leh_mut.get(i);
		    }
		    Solution new_sol = new Solution(Solution.leh2route(cross, n));
		    newGen.add(new_sol);
		}
	    return newGen;
	}

}
