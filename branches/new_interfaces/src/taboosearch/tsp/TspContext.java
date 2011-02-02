package taboosearch.tsp;

import taboosearch.Context;
import taboosearch.Evaluator;
import taboosearch.GenerationFabric;
import taboosearch.Taboolator;


public class TspContext extends Context<TspSolution, TspGeneration> {
	
	private int ticks = 0;
	private TspEvaluator evaluator;
	private TspTaboolator taboolator;
	
	public void tick() {
		this.ticks++;
		taboolator.tick();
	}
	
	public int getTicks() {
		return ticks;
	}
	
	@Override
	public TspEvaluator getEvaluator() {
		return evaluator;
	}
	
	@Override
	public TspTaboolator getTaboolator() {
		return taboolator;
	}
	
	@Override
	public void setEvaluator(Evaluator<TspSolution> evaluator) {
		this.evaluator = (TspEvaluator)evaluator;
	}

	@Override
	public void setTaboolator(Taboolator<TspSolution> taboolator) {
		this.taboolator = (TspTaboolator)taboolator;	
	}
	
	@Override
	public GenerationFabric<TspSolution, TspGeneration> getGenerationFabric() {
		return new TspGenerationFabric();
	}


	
}