package taboosearch.tsp;

import taboosearch.AdmissibleChecker;
import taboosearch.EliteCandidateList;

public class TSPEliteCandidateList extends EliteCandidateList<TSPSolution,
														   TSPSwapMove,
														   TSPGeneration,
														   TSPContext> {
	public TSPEliteCandidateList(
			int size,
			AdmissibleChecker<TSPSolution, TSPSwapMove, TSPGeneration, TSPContext> admissibleChecker,
			TSPContext context) {
		super(size, admissibleChecker, context);
	}

}
