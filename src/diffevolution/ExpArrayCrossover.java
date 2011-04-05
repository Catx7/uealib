package diffevolution;

import java.util.Random;


public class ExpArrayCrossover<S extends ArraySolution> implements diffevolution.AbstractCrossoverFabric<S>{
	
	public void doCrossover(S probe, S donor) {
		Random rand = new Random();
    	for(int inx = rand.nextInt(donor.length()); inx < donor.length(); ++inx)
        	probe.set(inx, donor.get(inx));
	}
	
}
