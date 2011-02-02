package taboosearch.tsp;

public class TSPSolution extends taboosearch.Solution {
	// immutable
	final private TSPSalesmanRoute route;
	final private double routeCost;
	final private TSPSwapMove move;

	public TSPSolution(TSPSalesmanRoute route, double routeCost, TSPSwapMove move) {
		this.route = route;
		this.routeCost = routeCost;
		this.move = move;
	}
	
	public TSPSolution(TSPSalesmanRoute route, double routeCost) {
		this(route, routeCost, null);
	}
	
	public TSPSalesmanRoute getRoute() {
		return this.route;
	}
	
	public double getRouteCost() {
		return this.routeCost;
	}
	
	public TSPSwapMove getMove() {
		return move;
	}
	
	public boolean isLazy() {
		return this.move != null;
	}

	public TSPSalesmanRoute unlazify() {
		if (this.move != null) {
			int[] routeArray = this.route.toArray();
			int i = this.move.getI(),
				j = this.move.getJ();
			
			int tmp = routeArray[i];
			routeArray[i] = routeArray[j];
			routeArray[j] = tmp;
			
			return new TSPSalesmanRoute(routeArray);
		} else {
			return this.route;
		}
	}

	@Override
	public String getStringRepresentation() {
		StringBuilder result = new StringBuilder();
		result.append("[ ");
		
		TSPSalesmanRoute route = this.unlazify();
		result.append(route.get(0) + 1);
		for (int i = 1; i < route.length(); ++i) {
			result.append(", ");
			result.append(route.get(i) + 1);
		}

		result.append(" ] ");
		return result.toString();
	}

	@Override
	public TSPSolution copy() {
		return new TSPSolution(route, routeCost, move);
	}

}
