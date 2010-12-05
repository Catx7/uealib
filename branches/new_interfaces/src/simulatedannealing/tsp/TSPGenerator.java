package simulatedannealing.tsp;

import java.util.Arrays;
import java.util.Random;

import simulatedannealing.GenerationList;
import core.Generator;
import core.Solution;

public class TSPGenerator implements Generator<GenerationList> {

	@Override
	public GenerationList getNext(GenerationList g) {
		TSPWay s = (TSPWay)g.get(0);
		
		TSPWay next = new TSPWay(s.getWay().clone());
		
		Random r = new Random();
		int i = r.nextInt(next.getWay().length);
		int j = r.nextInt(next.getWay().length);
		
		int[] way = next.getWay();
		
		int temp = way[i];
		way[i] = way[j];
		way[j] = temp;
		
		GenerationList result = new GenerationList();
		result.add(next);
		
		return result;
		
	}

}
