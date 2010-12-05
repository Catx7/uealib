package simulatedannealing;

import core.Solution;
import core.TransitionCriteria;

/** 
 * Стандартное правило перехода для Simulated Annealing
 *
 */

public class MetropolisRule implements TransitionCriteria<GenerationList> {

	@Override
	public boolean isSatisfied(GenerationList g, GenerationList h) {
		SimulatedAnnealingContext ctx = SimulatedAnnealingContext.getInstance();		
		Evaluator e = ctx.getEvaluator();
		double t = ctx.getShedule().getTemperature();
		
		Solution current = g.get(0);
		Solution next = h.get(0);
		
		int cmp = e.compare(current, next);
		
		if(cmp < 0) {
			return true;
		}
		
		else if(cmp==0)
			return false;
		
		double delta = Math.abs(e.evaluate(current)-e.evaluate(next));
		
		
		if(Math.random() < Math.exp(-delta/t)) {
			return true;
		}
		
		return false;
	}

}
