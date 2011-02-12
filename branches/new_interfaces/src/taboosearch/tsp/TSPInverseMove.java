package taboosearch.tsp;

import taboosearch.permutations.InverseMove;

public class TSPInverseMove extends InverseMove<TSPSolution>  {
	private int i, j;
	
	public TSPInverseMove(int i, int j) {
		super(i, j);
	}

	@Override
	public InverseMove<TSPSolution> clone() {
		return new TSPInverseMove(i, j);
	}

	@Override
	public TSPSolution makeSolution() {
		return null;
	}

	@Override
	public TSPSolution makeSolution(int[] permutation) {
		return new TSPSolution(permutation);
	}

}
