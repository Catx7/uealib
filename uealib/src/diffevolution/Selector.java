package diffevolution;

import java.util.ArrayList;

import diffevolution.Solution;
import diffevolution.Generation;

public class Selector implements core.Selector{

	public Generation keepTheBestSolutions(core.Generation g, core.Generation currentGeneration) {
		int n = ((Generation) g).get(0).size();
		for(int inx = 0; inx < Context.Gn; ++inx){
			int[] sol = new int[n];

            if (((Generation) g).get(inx).getFitness() < (((Generation) currentGeneration).get(inx)).getFitness()) 
                for(int i = 0; i < n; ++i)  
                 	sol[i] = ((Solution) currentGeneration.get(inx)).get(i);
            else
            	 for(int i = 0; i < n; ++i)  
                  	sol[i] = ((Solution) g.get(inx)).get(i);
            	
            g.add(new Solution(sol));
        }
		return (Generation) g;
	}
}
