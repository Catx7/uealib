package taboosearch;

public class Selector<S extends Solution,
					  G extends Generation<S>,
					  C extends Context<S, G>> implements core.Selector<G> {
	private C context;
	
	public Selector(C context) {
		this.context = context;
	}
	
	public G keepTheBestSolutions(G generation, G currentGeneration)  {
		Taboolator<S> taboolator = context.getTaboolator();
		Evaluator<S> evaluator 	 = context.getEvaluator();
		
		double candidateFitness = Double.MAX_VALUE;
		S candidate = null;
		
		for (S solution : generation) {
			if (!taboolator.isTabu(solution)) {
				double fitness = evaluator.evaluate(solution);
				if (fitness < candidateFitness) {
					candidateFitness = fitness;
					candidate = solution;
				}
			}
		}

		taboolator.setTabu(candidate);
		
		G result = context.getGenerationFabric().makeGeneration();
		result.add(candidate);
		return result;
	}

}
