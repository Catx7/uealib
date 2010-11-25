package simulatedannealing;

import core.Generation;
import core.Solution;

/** 
 * Стандартное правило перехода для Simulated Annealing
 *
 */

public class MetropolisRule implements core.TransitionCriteria {

	@Override
	public boolean isSatisfied(Generation g, Generation h) {
		SimulatedAnnealingContext ctx = SimulatedAnnealingContext.getInstance();		
		double t = ctx.getShedule().getTemperature();
		
		Solution current = (Solution)g.get(0);
		Solution next = (Solution)h.get(0);
		
		if(current.compareTo(next) < 0) {
			return true;
		}
		
		double delta = Math.abs(current.getFitness()-next.getFitness());
		
		
		if(Math.random() < Math.exp(-delta/t)) {
			return true;
		}
		
		return false;
	}

}
