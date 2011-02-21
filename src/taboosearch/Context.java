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


	protected XYSeries series;
	
	public S bestSolutionEver;
	
	public double bestSolutionEverCost;
	public int bububu = 200; 
	
	public Context() {		
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
