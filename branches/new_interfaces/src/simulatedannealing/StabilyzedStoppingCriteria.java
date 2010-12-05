package simulatedannealing;

import core.StoppingCriteria;
/**
 * Остановка, если даже при  <code> stopCount </code> итерациях текущее 
 * решение не изменилось
 *
 */


public class StabilyzedStoppingCriteria implements StoppingCriteria<GenerationList> {
	private int stopCount;
	
	public StabilyzedStoppingCriteria(int count) {
		stopCount = count;
	}
	
	@Override
	public boolean isSatisfied(GenerationList g) {
		SimulatedAnnealingContext ctx = SimulatedAnnealingContext.getInstance();
		if(ctx.getCount() >= stopCount)
			return true;
		
		return false;
	}

}
