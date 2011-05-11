package simulatedannealing.tsp;

import java.util.Random;

import simulatedannealing.IGenerator;
import core.Generator;

public class TSPGenerator implements IGenerator<TSPWay> {

	@Override
	public TSPWay getNext(TSPWay s) {		
		TSPWay next = new TSPWay(s.getWay().clone());
		
		Random r = new Random();
		int i = r.nextInt(next.getWay().length);
		int j = r.nextInt(next.getWay().length);
		
		int[] way = next.getWay();
		
		int temp = way[i];
		way[i] = way[j];
		way[j] = temp;
		
		return next;
		
	}

}
