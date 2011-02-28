package taboosearch;

import org.jfree.data.xy.XYSeries;

public class Context<S extends Solution, M extends Move<S>, G extends Generation<S>>
				implements common.TickableContext {
	protected int ticks = 0;
	protected XYSeries series;
	public S bestSolutionEver;
	public double bestSolutionEverCost; 
	
	public Context() {
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
