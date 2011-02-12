package taboosearch.cbir;

import taboosearch.AdmissibilityChecker;
import taboosearch.Context;
import taboosearch.EliteCandidateList;

public class CBIREliteCandidateList extends EliteCandidateList<CBIRSolution, CBIRSwapMove> {
	public CBIREliteCandidateList(
			int size,
			AdmissibilityChecker<CBIRSolution, CBIRSwapMove> admissibilityChecker,
			Context<CBIRSolution, CBIRSwapMove, CBIRGeneration> context) {
		super(size, admissibilityChecker, context.getEvaluator());
	}
}
