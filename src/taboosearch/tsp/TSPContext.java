package taboosearch.tsp;

import taboosearch.Context;
import taboosearch.Evaluator;
import taboosearch.GenerationFabric;
import taboosearch.Taboolator;


public class TSPContext extends Context<TSPSolution, TSPGeneration> {
	
	private int ticks = 0;
	private TSPEvaluator evaluator;
	private TSPTaboolator taboolator;
	
	public void tick() {
		this.ticks++;
		taboolator.tick();
	}
	
	public int getTicks() {
		return ticks;
	}
	
	@Override
	public TSPEvaluator getEvaluator() {
		return evaluator;
	}
	
	@Override
	public TSPTaboolator getTaboolator() {
		return taboolator;
	}
	
	@Override
	public void setEvaluator(Evaluator<TSPSolution> evaluator) {
		this.evaluator = (TSPEvaluator)evaluator;
	}

	@Override
	public void setTaboolator(Taboolator<TSPSolution> taboolator) {
		this.taboolator = (TSPTaboolator)taboolator;	
	}
	
	@Override
	public GenerationFabric<TSPSolution, TSPGeneration> getGenerationFabric() {
		return new TSPGenerationFabric();
	}


	
}