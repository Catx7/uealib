package taboosearch.cbir;

import taboosearch.permutations.InverseMove;

public class CBIRInverseMove extends InverseMove<CBIRSolution> {

	public CBIRInverseMove(int i, int j) {
		super(i, j);
	}

	@Override
	public CBIRInverseMove clone() {
		return new CBIRInverseMove(i, j);
	}

	@Override
	public CBIRSolution makeSolution(int[] permutation) {
		return null;
	}

}
