package taboosearch.cbir;

import taboosearch.permutations.InverseMove;
import taboosearch.tsp.TSPSolution;

public class CBIRInverseMove extends InverseMove<CBIRSolution> {

	public CBIRInverseMove(int i, int j) {
		super(i, j);
	}

	@Override
	public CBIRInverseMove clone() {
		return new CBIRInverseMove(i, j);
	}

	@Override
	public CBIRSolution makeSolution() {
		return null;
	}

	@Override
	public CBIRSolution makeSolution(int[] permutation) {
		new TSPSolution(permutation);
		return null;
	}

}
