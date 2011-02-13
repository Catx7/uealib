package taboosearch.cbir;

import taboosearch.AdmissibilityChecker;
import taboosearch.Evaluator;
import taboosearch.FrequencyMemory;
import taboosearch.Taboolator;

public class CBIRAdmissibilityChecker extends AdmissibilityChecker<CBIRSolution, CBIRSwapMove> {
	public CBIRAdmissibilityChecker(Evaluator<CBIRSolution, CBIRSwapMove> evaluator,
								   Taboolator<CBIRSolution, CBIRSwapMove> taboolator,
								   FrequencyMemory<CBIRSolution, CBIRSwapMove> frequencyMemory) {
		super(evaluator, taboolator);
	}
}
