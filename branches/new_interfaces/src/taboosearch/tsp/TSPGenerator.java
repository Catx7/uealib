package taboosearch.tsp;

import java.util.ArrayList;
import java.util.List;

import common.Pair;

import readers.Graph;
import taboosearch.Generator;

public class TSPGenerator extends Generator<TSPSolution, TSPSwapMove, TSPGeneration> {
	private TSPContext context;
	private TSPSwapMove[] staticMoves;
	
	public TSPGenerator(Graph graph, TSPContext context) {	
		this.context = context;
		this.staticMoves = getMoves(graph.getVertexesNumber());
		for (TSPSwapMove move : this.staticMoves) {
			this.context.staticMoves.add(move);
		}
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

	public Pair<TSPSolution, List<TSPSwapMove>> getNext(TSPGeneration generation) {
		assert generation.size() == 1;
		
		TSPSolution solution = generation.get(0);
		//TSPSwapMove[] moves = this.staticMoves;
		
		List<TSPSwapMove> result = new ArrayList<TSPSwapMove>();
		//if (!context.eliteList.needsToRebuild()) {
		//	//TSPSwapMove move = context.eliteList.getMove();
		//	//System.out.println(move);
		//	result.addAll(context.eliteList.getMoves());//(move);
		//} else {
		//	for (TSPSwapMove move : moves) {
		//		result.add(move);
		//	}
		//}
		
		return new Pair<TSPSolution, List<TSPSwapMove>>(solution, result);
	}

}
