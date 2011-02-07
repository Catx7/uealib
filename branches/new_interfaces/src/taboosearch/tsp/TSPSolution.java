package taboosearch.tsp;

import java.util.List;

public class TSPSolution extends taboosearch.Solution { // immutable
	final protected int[] route;
	private double cost;
	
	public TSPSolution(int[] route) {
		this.route = route.clone();
	}
	
	public TSPSolution(List<Integer> route) {
		this.route = new int[route.size()];
		for (int i = 0; i < this.route.length; ++i)
			this.route[i] = route.get(i);
	}
	
	public int length() {
        return this.route.length;
	}
	
	public int get(int index) {
		return this.route[index];
	}
	
	public int[] toArray() {
		return this.route.clone();
	}

	@Override
	public String getStringRepresentation() {
		StringBuilder result = new StringBuilder();
		result.append("[ ");
		
		result.append(route[0] + 1);
		for (int i = 1; i < route.length; ++i) {
			result.append(", ");
			result.append(route[i] + 1);
		}

		result.append(" ] ");
		return result.toString();
	}

	@Override
	public TSPSolution copy() {
		return new TSPSolution(route);
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getCost() {
		return cost;
	}

}
