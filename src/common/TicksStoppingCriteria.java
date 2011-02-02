package common;

public class TicksStoppingCriteria<S extends core.Solution,
								   G extends core.Generation<S>,
								   C extends TickableContext> implements core.StoppingCriteria<G> {
	private int ticks;
	private C context;
	
	public TicksStoppingCriteria(C context, int ticks) {
		this.context = context;
		this.ticks = ticks;
	}
	
	public boolean isSatisfied(G g) {
		return (context.getTicks() == ticks);
	}
}
