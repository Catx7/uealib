package taboosearch;

import java.util.ArrayList;
import java.util.List;
import org.jfree.data.xy.XYSeries;
import common.AbstractGenerationFabric;

public class Context<S extends Solution, M extends Move<S>, G extends Generation<S>>
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
	public int bububu = 200; 
	
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
	
	public synchronized void setCurrentSolution(S solution, double cost) {
		if (cost < bestSolutionEverCost) {
			bestSolutionEver = solution;
			bestSolutionEverCost = cost;
			System.err.println("NEW: " + solution.getStringRepresentation() + " " + cost);
		}
		//series.add(ticks, cost);
	}
	
	public XYSeries getSeries() {
		return series;
	}
	
	public void tick() {
		++this.ticks;
	}
	
	public int getTicks() {
		return ticks;
	}
	
}
