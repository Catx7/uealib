package taboosearch;

public class TicksStoppingCriteria<S extends Solution,
								  G extends Generation<S>,
								  C extends Context<S, G>> implements core.StoppingCriteria<G> {

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
