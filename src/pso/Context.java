package pso;

public class Context<G extends pso.Generation<S>, S extends Solution<S>>
		implements common.TickableContext {

	protected int ticks = 0;
	protected int equal;

	private Evaluator<S> evaluator;

	public Context(Evaluator<S> evaluator) {
		this.setEvaluator(evaluator);
	}

	public void setEvaluator(Evaluator<S> evaluator) {
		this.evaluator = evaluator;
	}

	public Evaluator<S> getEvaluator() {
		return evaluator;
	}

	public void tick() {
		this.ticks++;
	}
	
	public int getTicks() {
		return ticks;
	}

	public void incEqual() {
		this.equal++;
	}
	
	public void dumpEqual() {
		this.equal = 0;
	}
}
