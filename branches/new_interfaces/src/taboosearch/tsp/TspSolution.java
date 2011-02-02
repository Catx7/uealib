package taboosearch.tsp;

public class TspSolution extends taboosearch.Solution {
	// immutable
	final private TspSalesmanRoute route;
	final private double routeCost;
	final private TspSwapMove move;

	public TspSolution(TspSalesmanRoute route, double routeCost, TspSwapMove move) {
		this.route = route;
		this.routeCost = routeCost;
		this.move = move;
	}
	
	public TspSolution(TspSalesmanRoute route, double routeCost) {
		this(route, routeCost, null);
	}
	
	public TspSalesmanRoute getRoute() {
		return this.route;
	}
	
	public double getRouteCost() {
		return this.routeCost;
	}
	
	public TspSwapMove getMove() {
		return move;
	}
	
	public boolean isLazy() {
		return this.move != null;
	}

	public TspSalesmanRoute unlazify() {
		if (this.move != null) {
			int[] routeArray = this.route.toArray();
			int i = this.move.getI(),
				j = this.move.getJ();
			
			int tmp = routeArray[i];
			routeArray[i] = routeArray[j];
			routeArray[j] = tmp;
			
			return new TspSalesmanRoute(routeArray);
		} else {
			return this.route;
		}
	}

	@Override
	public String getStringRepresentation() {
		StringBuilder result = new StringBuilder();
		result.append("[ ");
		
		TspSalesmanRoute route = this.unlazify();
		result.append(route.get(0) + 1);
		for (int i = 1; i < route.length(); ++i) {
			result.append(", ");
			result.append(route.get(i) + 1);
		}

		result.append(" ] ");
		return result.toString();
	}

	@Override
	public TspSolution copy() {
		return new TspSolution(route, routeCost, move);
	}

}
