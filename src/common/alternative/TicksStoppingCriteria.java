package common.alternative;

import common.TickableContext;

public class TicksStoppingCriteria<S extends core.Solution, C extends TickableContext> {
	private int ticks;
	
	public TicksStoppingCriteria(int ticks) {
		this.ticks = ticks;
	}
	
	public synchronized boolean isSatisfied(S solution, C context) {
		return (context.getTicks() >= ticks);
	}
}
