package diffevolution;



public class Context<G extends diffevolution.Generation<S>, S extends ArraySolution<?>, Cr extends AbstractCrossoverFabric<S>>
		implements common.TickableContext {

	protected int ticks;

	private Evaluator<S> evaluator;
	private Cr strategy;
	private SimpleArrayCrossover defaultCrossoverStrategy;
	
	public Cr getCrossoverStrategy() {
		return this.strategy;
	}

	public Context(Evaluator<S> evaluator) {
		this.setEvaluator(evaluator);
		this.setCrossoverStrategy((Cr) defaultCrossoverStrategy);
	} 
	
	public Context(Evaluator<S> evaluator, Cr crossoverFabric) {
		this.setEvaluator(evaluator);
		this.setCrossoverStrategy(crossoverFabric);
	}

	public void setEvaluator(Evaluator<S> evaluator) {
		this.evaluator = evaluator;
	}
	
	public void setCrossoverStrategy(Cr strategy) {
		this.strategy = strategy;
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
