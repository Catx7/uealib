package simulatedannealing.tsp;

import java.util.ArrayList;

import core.Solution;

public class TSPWay extends Solution{
	private int[] way;
 	
	public TSPWay(int[] way) {
		this.way = way;
		
	}
	
	public int[] getWay() {
		return way;
	}
	
	
	
	public double getFitness() {
		// TODO Auto-generated method stub
		return 0;
	}

}
