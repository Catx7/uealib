package simulatedannealing;

import core.Solution;
import core.TransitionCriteria;

/**
 * Стандартное правило перехода для Simulated Annealing
 * 
 */

public class MetropolisRule implements ITransitionCriteria {

	private SimulatedAnnealingContext ctx;
	
	public MetropolisRule(SimulatedAnnealingContext ctx) {
		super();
		this.ctx = ctx;
	}


	@Override
	public boolean isSatisfied(Solution current, Solution next) {
		IEvaluator e = ctx.getEvaluator();
		double t = ctx.getShedule().getTemperature();

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
