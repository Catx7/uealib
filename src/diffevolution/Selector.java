package diffevolution;

import diffevolution.Context;
import diffevolution.Generation;
import diffevolution.Solution;

public class Selector < S extends Solution<S>,
						G extends Generation<S>, 
						C extends Context<G, S>>
						implements core.Selector<G> {
	
	private C context;
	
	public Selector(C context) {
		this.context = context;
	}

	@Override
	public G keepTheBestSolutions(G g, G currentGeneration) {
		for (int i = 0; i < g.size(); ++i) {
			if (context.getEvaluator().evaluate(g.get(i)) < context.getEvaluator().evaluate(currentGeneration.get(i))) {
				currentGeneration.set(i, g.get(i));
			}			
		}
		return currentGeneration;
	}

}