package common;

public class TicksStoppingCriteria<S extends core.Solution,
								   G extends core.Generation<S>,
								   C extends TickableContext> {
	private int ticks;
	
	public TicksStoppingCriteria(int ticks) {
		this.ticks = ticks;
	}
	
	public synchronized boolean isSatisfied(G g, C context) {
		return (context.getTicks() >= ticks);
	}
}
