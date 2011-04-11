package taboosearch.knapsack;

import org.jfree.data.xy.XYSeries;
import taboosearch.Context;

public class KPContext extends Context<KPSolution, KPMove> {
	public KPContext() {
		this.bestSolutionEverCost = Double.MAX_VALUE;
		this.series = new XYSeries("Cost");
	}
	
	public synchronized void setCurrentSolution(KPSolution solution, double cost) {
		if (cost < bestSolutionEverCost) {
			bestSolutionEver = solution;
			bestSolutionEverCost = cost;
			System.err.println("NEW: " + solution.getStringRepresentation() + " " + cost);
		}
		series.add(ticks, cost);
	}
}
