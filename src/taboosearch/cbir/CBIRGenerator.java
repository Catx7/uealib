package taboosearch.cbir;

import java.util.ArrayList;
import java.util.List;

import common.Pair;

import taboosearch.Generator;
import taboosearch.readers.FeaturesSpace;

public class CBIRGenerator extends Generator<CBIRSolution, CBIRSwapMove, CBIRGeneration> {
	private CBIRContext context;
	private CBIRSwapMove[] staticMoves;
	
	public CBIRGenerator(FeaturesSpace space, CBIRContext context) {	
		this.context = context;
		this.staticMoves = getMoves(space.n);
		for (CBIRSwapMove move : this.staticMoves) {
			this.context.staticMoves.add(move);
		}
	}
	
	private CBIRSwapMove[] getMoves(int n) {
		CBIRSwapMove[] result = new CBIRSwapMove[(n - 2) * (n - 1) / 2];
		int idx = 0;
		
		for (int i = 1; i < n - 1; ++i)
			for (int j = i + 1; j < n; ++j) {
				result[idx++] = new CBIRSwapMove(i, j);
			}
		
		return result;	
	}

	public Pair<CBIRSolution, List<CBIRSwapMove>> getNext(CBIRGeneration generation) {
		assert generation.size() == 1;
		
		CBIRSolution solution = generation.get(0);
		//TSPSwapMove[] moves = this.staticMoves;
		
		List<CBIRSwapMove> result = new ArrayList<CBIRSwapMove>();
		//if (!context.eliteList.needsToRebuild()) {
		//	//TSPSwapMove move = context.eliteList.getMove();
		//	//System.out.println(move);
		//	result.addAll(context.eliteList.getMoves());//(move);
		//} else {
		//	for (TSPSwapMove move : moves) {
		//		result.add(move);
		//	}
		//}
		
		return new Pair<CBIRSolution, List<CBIRSwapMove>>(solution, result);
	}

}
