package taboosearch.tsp;

import taboosearch.Generator;

public class TspGenerator extends Generator<TspSolution, TspGeneration> {
	private TspContext context;
	
	public TspGenerator(TspContext context) {	
		this.context = context;
	}
	
	private TspSwapMove[] getMoves(int n) {
		TspSwapMove[] result = new TspSwapMove[(n - 2) * (n - 1) / 2];
		int idx = 0;
		
		for (int i = 1; i < n - 1; ++i)
			for (int j = i + 1; j < n; ++j) {
				result[idx++] = new TspSwapMove(i, j);
			}
		
		return result;	
	}

	@Override
	public TspGeneration getNext(TspGeneration generation) {
		assert generation.size() == 1;
		
		TspSolution solution = generation.get(0);
		TspSalesmanRoute route;
		if (solution.isLazy()) {
			route = solution.unlazify();
		} else {
			route = solution.getRoute();
		}
		double routeCost = context.getEvaluator().evaluate(route);
		
		TspSwapMove[] moves = this.getMoves(route.length());
		
		TspGeneration result = new TspGeneration();
		for (TspSwapMove move : moves) {
			result.add(new TspSolution(route, routeCost, move));
		}
		
		return result;
	}

}
