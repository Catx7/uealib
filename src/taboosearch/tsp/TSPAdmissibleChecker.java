package taboosearch.tsp;

import taboosearch.AdmissibleChecker;

public class TSPAdmissibleChecker extends AdmissibleChecker<TSPSolution,
															TSPSwapMove,
															TSPGeneration,
															TSPContext> {
	public TSPAdmissibleChecker(TSPContext context) {
		super(context);
	}	
}
