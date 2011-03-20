package simulatedannealing;

import core.Solution;
import core.TransitionCriteria;

/**
 * Стандартное правило перехода для Simulated Annealing
 * 
 */

public class MetropolisRule implements TransitionCriteria<GenerationList> {

	private SimulatedAnnealingContext ctx;
	
	public MetropolisRule(SimulatedAnnealingContext ctx) {
		super();
		this.ctx = ctx;
	}


	@Override
	public boolean isSatisfied(GenerationList g, GenerationList h) {
		Evaluator e = ctx.getEvaluator();
		double t = ctx.getShedule().getTemperature();

		Solution current = g.get(0);
		Solution next = h.get(0);

		int cmp = e.compare(current, next);

		if (cmp < 0) {
			return true;
		}

		else if (cmp == 0)
			return false;

		double delta = Math.abs(e.evaluate(current) - e.evaluate(next));

		if (Math.random() < Math.exp(-delta / t)) {
			return true;
		}

		return false;
	}

}
