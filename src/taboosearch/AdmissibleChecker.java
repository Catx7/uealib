package taboosearch;

public class AdmissibleChecker<S extends Solution,
							   M extends Move<S>,
							   G extends Generation<S>,
							   C extends Context<S, M, G> > {
	
	private C context;
	private Evaluator<S, M> evaluator;
	private Taboolator<S, M> taboolator;
	private FrequencyMemory<S, M> frequencyMemory;
	
	public AdmissibleChecker(C context) {
		this.context = context;
		this.taboolator = context.getTaboolator();
		this.frequencyMemory = context.getFrequencyMemory();
		this.evaluator = context.getEvaluator();
	}
	
	public boolean isAdmissible(S solution, M move) {	
		if (!taboolator.isTabu(solution, move)) {
			return true;
		} else {
			double solutionCost = evaluator.evaluate(solution, move);
			return (solutionCost < context.bestSolutionEverCost); // aspiration criteria
		}
	}

}
