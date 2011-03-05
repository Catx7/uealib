package diffevolution.knapsack;

import java.util.Random;

import core.Generator;
import diffevolution.Context;


public class KnapsackGenerator<C extends Context<KnapsackGeneration, KnapsackSolution>> 
	implements Generator<KnapsackGeneration> {

	private static final int DEFAULT_F = 1;
	private int F = DEFAULT_F;

	public KnapsackGeneration getNext(KnapsackGeneration currentGeneration) {
		KnapsackGeneration g = new KnapsackGeneration();
		int Gn = currentGeneration.size(); 
			
		for(int k = 0; k < Gn; ++k) {			
		    int x = 1, y = 1, z = 1;
		    Random rand = new Random();
	
		    while(x == y || y == z ){
		        x = rand.nextInt(Gn);
		    	y = rand.nextInt(Gn);
		    	z = rand.nextInt(Gn);
		    }
		    		  
		    KnapsackSolution newRoute = currentGeneration.mutate(currentGeneration.get(x), currentGeneration.get(y), currentGeneration.get(z), this.getF());  
		    newRoute.doCrossover(currentGeneration.get(k));
		    
		    g.add(newRoute);
		}
	    return g;	
	}

	public void setF(int F) {
		this.F = F;
	}
	
	private int getF() {
		return this.F;
	}

}
