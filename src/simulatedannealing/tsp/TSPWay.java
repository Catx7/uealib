package simulatedannealing.tsp;

import simulatedannealing.AbstractSolution;
import core.Solution;

public class TSPWay extends AbstractSolution{
	private int[] way;
	

	public TSPWay(int[] way) {
		super();
		this.way = way;
	}
	
	public int[] getWay() {
		return way;
	}
	
	@Override
	public Solution copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
