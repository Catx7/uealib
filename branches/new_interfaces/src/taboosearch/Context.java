package taboosearch;

import java.util.ArrayList;
import java.util.List;
import org.jfree.data.xy.XYSeries;
import common.AbstractGenerationFabric;

public abstract class Context<S extends Solution, M extends Move<S>, G extends Generation<S>>
				implements common.TickableContext {
	public EliteCandidateList<S, M> eliteList;
	public List<M> staticMoves;
	
	protected int ticks = 0;
	protected Evaluator<S, M> evaluator;
	protected Taboolator<S, M> taboolator;
	protected FrequencyMemory<S, M> frequencyMemory;
	protected AbstractGenerationFabric<S, G> generationFabric;

	protected XYSeries series;
	
	public S bestSolutionEver;
	
	public double bestSolutionEverCost;
	
	public Context(Evaluator<S, M> evaluator,
				   Taboolator<S, M> taboolator,
				   FrequencyMemory<S, M> frequencyMemory,
				   AbstractGenerationFabric<S, G> generationFabric,
				   EliteCandidateList<S, M> eliteList) {
		this.evaluator = evaluator;
		this.taboolator	= taboolator;
		this.frequencyMemory = frequencyMemory;
		this.eliteList = eliteList;
		this.generationFabric = generationFabric;
		
		this.staticMoves = new ArrayList<M>();
		
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
	
	public void tick() {
		this.ticks++;
	}
	
	public EliteCandidateList<S, M> getEliteList() {
		return eliteList;
	}

	public void setEliteList(EliteCandidateList<S, M> eliteList) {
		this.eliteList = eliteList;
	}

	public Evaluator<S, M> getEvaluator() {
		return evaluator;
	}

	public void setEvaluator(Evaluator<S, M> evaluator) {
		this.evaluator = evaluator;
	}

	public Taboolator<S, M> getTaboolator() {
		return taboolator;
	}

	public void setTaboolator(Taboolator<S, M> taboolator) {
		this.taboolator = taboolator;
	}

	public FrequencyMemory<S, M> getFrequencyMemory() {
		return frequencyMemory;
	}

	public void setFrequencyMemory(FrequencyMemory<S, M> frequencyMemory) {
		this.frequencyMemory = frequencyMemory;
	}
	
	public AbstractGenerationFabric<S, G> getGenerationFabric() {
		return generationFabric;
	}

	public int getTicks() {
		return ticks;
	}
	
}
