package taboosearch.tsp;

import taboosearch.AdmissibilityChecker;
import taboosearch.Context;
import taboosearch.EliteCandidateList;

public class TSPEliteCandidateList extends EliteCandidateList<TSPSolution, TSPSwapMove> {
	public TSPEliteCandidateList(
			int size,
			AdmissibilityChecker<TSPSolution, TSPSwapMove> admissibilityChecker,
			Context<TSPSolution, TSPSwapMove, TSPGeneration> context) {
		super(size, admissibilityChecker, context.getEvaluator());
	}
}
