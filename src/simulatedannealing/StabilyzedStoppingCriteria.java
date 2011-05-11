package simulatedannealing;



public class StabilyzedStoppingCriteria implements IStoppingCriteria {
	private int stopCount;
	private SimulatedAnnealingContext ctx;

	public StabilyzedStoppingCriteria(int count, SimulatedAnnealingContext ctx) {
		this.ctx = ctx;
		stopCount = count;
	}

	@Override
	public boolean isSatisfied() {
		if (ctx.getCount() >= stopCount)
			return true;

		return false;
	}

}
