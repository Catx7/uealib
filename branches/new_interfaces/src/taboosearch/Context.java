package taboosearch;

import java.util.List;

import org.jfree.data.xy.XYSeries;

public abstract class Context<S extends Solution,
							  M extends Move<S>,
							  G extends Generation<S>> implements common.TickableContext {
	public EliteCandidateList<S, M> eliteList;
	public List<M> staticMoves;
	
	protected int ticks = 0;
	protected Evaluator<S, M> evaluator;
	protected Taboolator<S, M> taboolator;
	protected FrequencyMemory<S, M> frequencyMemory;

	protected XYSeries series;
	
	public S bestSolutionEver;
	
	public double bestSolutionEverCost;
	
	
	public Context(Evaluator<S, M> evaluator,
				   Taboolator<S, M> taboolator,
				   FrequencyMemory<S, M> frequencyMemory) {
		this.evaluator = evaluator;
		this.taboolator	= taboolator;
		this.frequencyMemory = frequencyMemory;
		this.bestSolutionEverCost = Double.MAX_VALUE;
		this.series = new XYSeries("Cost");
	}
	
	public void setCurrentSolution(S solution, double cost) {
		if (cost < bestSolutionEverCost) {
			bestSolutionEver = solution;
			bestSolutionEverCost = cost;
		}
		series.add(ticks, cost);
	}
	
	public XYSeries getSeries() {
		return series;
	}
	
	public abstract void setTaboolator(Taboolator<S, M> taboolator);

	public abstract Taboolator<S, M> getTaboolator();
	
	public abstract void setEvaluator(Evaluator<S, M> evaluator);

	public abstract Evaluator<S, M> getEvaluator();
	
	public abstract GenerationFabric<S, G> getGenerationFabric();
		
	public abstract FrequencyMemory<S, M> getFrequencyMemory();

	public abstract void setFrequencyMemory(FrequencyMemory<S, M> frequencyMemory);
	
	public void tick() {
		this.ticks++;
	}
	
	public int getTicks() {
		return ticks;
	}
	
}
