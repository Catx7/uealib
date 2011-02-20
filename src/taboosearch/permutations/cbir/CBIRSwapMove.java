package taboosearch.permutations.cbir;

import taboosearch.permutations.SwapMove;

//immutable
public class CBIRSwapMove extends SwapMove<CBIRSolution> {

	public CBIRSwapMove(int i, int j) {
		super(i, j);
	}

	@Override
	public SwapMove<CBIRSolution> clone() {
		return new CBIRSwapMove(i, j);
	}
	@Override
	public CBIRSolution makeSolution(int[] permutation) {
		return new CBIRSolution(permutation);
	}

}
