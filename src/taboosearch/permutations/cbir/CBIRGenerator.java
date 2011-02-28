package taboosearch.permutations.cbir;

import taboosearch.permutations.AbstractMoveFabric;

public class CBIRGenerator
	extends taboosearch.permutations.Generator<CBIRSolution, CBIRSwapMove, CBIRGeneration> {

	public CBIRGenerator(
			int n,
			AbstractMoveFabric<? extends CBIRSolution, ? extends CBIRSwapMove> moveFabric) {
		super(n, moveFabric);
		// TODO Auto-generated constructor stub
	}


}
