package diffevolution;

public class Context<G extends diffevolution.Generation<S>, S extends Solution<S>>
		implements common.TickableContext {

	protected int ticks;

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

}
