package taboosearch;

import core.Generation;

public class TickStoppingCriteria implements core.StoppingCriteria {

	private int ticks;
	
	public TickStoppingCriteria(int ticks) {
		this.ticks = ticks;
	}
	
	@SuppressWarnings("unchecked")
	public boolean isSatisfied(Generation g) {
		Context ctx = Context.getInstance();
		return (ctx.getTicks() == ticks);
	}

}
