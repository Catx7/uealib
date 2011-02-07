package taboosearch;

public class AdmissibilityChecker<S extends Solution,
								  M extends Move<S>> {
	private Evaluator<S, M> evaluator;
	private Taboolator<S, M> taboolator;
	@SuppressWarnings("unused")
	private FrequencyMemory<S, M> frequencyMemory;
	
	public AdmissibilityChecker(Evaluator<S, M> evaluator,
								Taboolator<S, M> taboolator,
								FrequencyMemory<S, M> frequencyMemory) {
		this.evaluator = evaluator;
		this.taboolator = taboolator;
		this.frequencyMemory = frequencyMemory;
	}
	
	public boolean isAdmissible(S solution, M move, double bestCostEver) {	
		if (!taboolator.isTabu(solution, move)) {
			return true;
		} else {
			double solutionCost = evaluator.evaluate(solution, move);
			return (solutionCost < bestCostEver); // aspiration criteria
		}
	}

}
