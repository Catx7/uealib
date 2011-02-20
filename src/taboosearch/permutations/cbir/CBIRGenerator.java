package taboosearch.permutations.cbir;

import taboosearch.permutations.MoveFabric;

public class CBIRGenerator
	extends taboosearch.permutations.Generator<CBIRSolution, CBIRSwapMove, CBIRGeneration> {

	public CBIRGenerator(
			int n,
			MoveFabric<? extends CBIRSolution, ? extends CBIRSwapMove> moveFabric) {
		super(n, moveFabric);
		// TODO Auto-generated constructor stub
	}


}
