package diffevolution.functions;

import java.util.Random;

import core.Generator;
import diffevolution.Context;

public class FGenerator implements Generator<FGeneration> {

	private static final int DEFAULT_F = 2;
	private int F = DEFAULT_F;
	private Context context;

	public FGenerator(Context context) {
		this.context = context;
	}
		
	public FGeneration getNext(FGeneration currentGeneration) {
		FGeneration g = new FGeneration();
		int Gn = currentGeneration.size(); 
			
		for(int k = 0; k < Gn; ++k) {			
		    int x = 1, y = 1, z = 1;
		    Random rand = new Random();
	
		    while(x == y || y == z ){
		        x = rand.nextInt(Gn);
		    	y = rand.nextInt(Gn);
		    	z = rand.nextInt(Gn);
		    }
		   
		    FSolution newRoute = currentGeneration.mutate(currentGeneration.get(x), currentGeneration.get(y), currentGeneration.get(z), this.getF());  
		    context.getCrossoverStrategy().doCrossover(newRoute, currentGeneration.get(k));
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

