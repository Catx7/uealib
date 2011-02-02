package taboosearch;

public abstract class Context<S extends Solution, G extends Generation<S>> implements common.TickableContext {
	
	public S bestSolutionEver;
	
	public double bestSolutionEverFitness;
	
	public abstract void setTaboolator(Taboolator<S> taboolator);

	public abstract Taboolator<S> getTaboolator();
	
	public abstract void setEvaluator(Evaluator<S> evaluator);

	public abstract Evaluator<S> getEvaluator();
	
	public abstract GenerationFabric<S, G> getGenerationFabric();
	
	public abstract void tick();
	
	public abstract int getTicks();
	
}
