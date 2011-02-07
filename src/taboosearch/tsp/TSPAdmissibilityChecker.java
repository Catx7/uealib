package taboosearch.tsp;

import taboosearch.AdmissibilityChecker;
import taboosearch.Evaluator;
import taboosearch.FrequencyMemory;
import taboosearch.Taboolator;

public class TSPAdmissibilityChecker extends AdmissibilityChecker<TSPSolution, TSPSwapMove> {
	public TSPAdmissibilityChecker(Evaluator<TSPSolution, TSPSwapMove> evaluator,
								   Taboolator<TSPSolution, TSPSwapMove> taboolator,
								   FrequencyMemory<TSPSolution, TSPSwapMove> frequencyMemory) {
		super(evaluator, taboolator, frequencyMemory);
	}
}
