package simulatedannealing;

import core.Generation;
/**
 * Остановка, если даже при  <code> stopCount </code> итерациях текущее 
 * решение не изменилось
 *
 */


public class StabilyzedStoppingCriteria implements core.StoppingCriteria {
	private int stopCount;
	
	public StabilyzedStoppingCriteria(int count) {
		stopCount = count;
	}
	
	@Override
	public boolean isSatisfied(Generation g) {
		SimulatedAnnealingContext ctx = SimulatedAnnealingContext.getInstance();
		if(ctx.getCount() >= stopCount)
			return true;
		
		return false;
	}

}
