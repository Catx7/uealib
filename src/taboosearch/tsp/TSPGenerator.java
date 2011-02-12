package taboosearch.tsp;

import readers.Graph;

public class TSPGenerator
	extends taboosearch.permutations.Generator<TSPSolution, TSPSwapMove, TSPGeneration> {

	public TSPGenerator(Graph graph) {
		super(graph);
	}

	@Override
	protected TSPSwapMove makeMove(int i, int j) {
		return new TSPSwapMove(i, j);
	}

}
