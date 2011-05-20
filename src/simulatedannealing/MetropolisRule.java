package simulatedannealing;

import core.Solution;
import core.TransitionCriteria;

/**
 * Стандартное правило перехода для Simulated Annealing
 * 
 */

public class MetropolisRule implements ITransitionCriteria {
	private ITemperatureShedule temperatureShedule;
	private IEvaluator evaluator;
	
	public MetropolisRule(ITemperatureShedule temperatureShedule, IEvaluator evaluator) {
		this.temperatureShedule = temperatureShedule;
		this.evaluator = evaluator;
	}


	@Override
	public boolean isSatisfied(Solution current, Solution next) {
		double t = temperatureShedule.getTemperature();

		int cmp = evaluator.compare(current, next);

		if (cmp < 0) {
			return true;
		}

		else if (cmp == 0)
			return false;

		double delta = Math.abs(evaluator.evaluate(current) - evaluator.evaluate(next));

		if (Math.random() < Math.exp(-delta / t)) {
			return true;
		}

		return false;
	}

}
