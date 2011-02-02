package taboosearch.tsp;

import taboosearch.Generator;

public class TSPGenerator extends Generator<TSPSolution, TSPGeneration> {
	private TSPContext context;
	
	public TSPGenerator(TSPContext context) {	
		this.context = context;
	}
	
	private TSPSwapMove[] getMoves(int n) {
		TSPSwapMove[] result = new TSPSwapMove[(n - 2) * (n - 1) / 2];
		int idx = 0;
		
		for (int i = 1; i < n - 1; ++i)
			for (int j = i + 1; j < n; ++j) {
				result[idx++] = new TSPSwapMove(i, j);
			}
		
		return result;	
	}

	@Override
	public TSPGeneration getNext(TSPGeneration generation) {
		assert generation.size() == 1;
		
		TSPSolution solution = generation.get(0);
		TSPSalesmanRoute route;
		if (solution.isLazy()) {
			route = solution.unlazify();
		} else {
			route = solution.getRoute();
		}
		double routeCost = context.getEvaluator().evaluate(route);
		
		TSPSwapMove[] moves = this.getMoves(route.length());
		
		TSPGeneration result = new TSPGeneration();
		for (TSPSwapMove move : moves) {
			result.add(new TSPSolution(route, routeCost, move));
		}
		
		return result;
	}

}
