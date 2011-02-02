package taboosearch.tsp;

import java.util.List;

public class TSPSalesmanRoute { // immutable
	final protected int[] route;
	
	public TSPSalesmanRoute(int[] route) {
		this.route = route.clone();
	}
	
	public TSPSalesmanRoute(List<Integer> route) {
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

}
