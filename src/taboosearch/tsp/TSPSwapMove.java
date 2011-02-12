package taboosearch.tsp;

import taboosearch.permutations.SwapMove;

// immutable
public class TSPSwapMove extends SwapMove<TSPSolution> {

	public TSPSwapMove(int i, int j) {
		super(i, j);
	}

	@Override
	public SwapMove<TSPSolution> clone() {
		return new TSPSwapMove(i, j);
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
