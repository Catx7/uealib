package taboosearch;

import taboosearch.exceptions.UnsupportedMoveType;

public class AdmissibilityChecker<S extends Solution, M extends Move<S>> {
	private Evaluator<S, M> evaluator;
	private Taboolator<S, M> taboolator;
	
	public AdmissibilityChecker(Evaluator<S, M> evaluator, Taboolator<S, M> taboolator) {
		this.evaluator = evaluator;
		this.taboolator = taboolator;
	}
	
	public boolean isAdmissible(S solution, M move, double bestCostEver) throws UnsupportedMoveType {	
		if (!taboolator.isTabu(solution, move)) {
			return true;
		} else {
			double solutionCost = evaluator.evaluate(solution, move);
			return (solutionCost < bestCostEver); // aspiration criteria
		}
	}
}
