package diffevolution;

import diffevolution.Generation;
import diffevolution.Solution;
import diffevolution.Context;

import java.util.Random;


public class Generator implements core.Generator{

	private int[] mutation(int[] leh_1, int[] leh_2, int[] leh_3, int n )
	{
		int[] result = new int[n];
	    for(int i = 0; i < n; ++i)
	        result[i] = (int) Math.abs((leh_1[i] + Context.F*(leh_2[i] - leh_3[i]) % (n - i)) % (n - i));
	     return result;
	}
	
	public Generation getNext(core.Generation g) {
		Generation newGen = new Generation();
		
		for(int k = 0; k < Context.Gn; ++k) {
			
		    int x=1, y=1, z=1;
		    Random rand = new Random();
	
		    while(x == y || y == z || z == x){
		        x = rand.nextInt(Context.Gn - 1);
		    	y = rand.nextInt(Context.Gn - 1);
		    	z = rand.nextInt(Context.Gn - 1);
		    }
		    int n = ((Generation) g.get(0)).size();
		    int[] leh_mut = new int[n];	// после mutation
		    int[] leh_curr = new int[n];	// после Crossover
		    leh_mut = mutation(Solution.route2leh(((Generation) g).get(x)), Solution.route2leh(((Generation) g).get(y)), Solution.route2leh(((Generation) g).get(z)), n);
		    leh_curr = Solution.route2leh(((Generation) g).get(k));	
		    
		    for(int i = 0; i < n; ++i){
		       double p = Math.random();
		       if (p <= Context.Cr) leh_curr[i] = leh_mut[i];
		    }
		    Solution.leh2route(leh_curr, n);
		    newGen.add(new Solution(leh_curr));
		}
	    return newGen;
	}

}
