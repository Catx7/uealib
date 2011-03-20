package simulatedannealing;

import core.StoppingCriteria;

/**
 * Остановка, если даже при <code> stopCount </code> итерациях текущее решение
 * не изменилось
 * 
 */

public class StabilyzedStoppingCriteria implements
		StoppingCriteria<GenerationList> {
	private int stopCount;
	private SimulatedAnnealingContext ctx;

	public StabilyzedStoppingCriteria(int count, SimulatedAnnealingContext ctx) {
		this.ctx = ctx;
		stopCount = count;
	}

	@Override
	public boolean isSatisfied(GenerationList g) {
		if (ctx.getCount() >= stopCount)
			return true;

		return false;
	}

}
