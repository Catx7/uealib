package taboosearch;

public class TickStoppingCriteria implements core.StoppingCriteria<LazyGeneration> {

	private int ticks;
	
	public TickStoppingCriteria(int ticks) {
		this.ticks = ticks;
	}
	
	public boolean isSatisfied(LazyGeneration g) {
		Context ctx = Context.getInstance();
		return (ctx.getTicks() == ticks);
	}

}
