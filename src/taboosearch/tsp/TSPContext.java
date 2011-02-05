package taboosearch.tsp;

import java.util.ArrayList;

import taboosearch.Context;
import taboosearch.Evaluator;
import taboosearch.FrequencyMemory;
import taboosearch.GenerationFabric;
import taboosearch.Taboolator;

public class TSPContext extends Context<TSPSolution, TSPSwapMove, TSPGeneration> {
	
	public TSPContext(Evaluator<TSPSolution, TSPSwapMove> evaluator,
					  Taboolator<TSPSolution, TSPSwapMove> taboolator,
					  FrequencyMemory<TSPSolution, TSPSwapMove> frequencyMemory) {
		super(evaluator, taboolator, frequencyMemory);
		this.eliteList = new TSPEliteCandidateList(10, new TSPAdmissibleChecker(this), this);
		this.staticMoves = new ArrayList<TSPSwapMove>();
	}

	@Override
	public TSPEvaluator getEvaluator() {
		return (TSPEvaluator)evaluator;
	}
	
	@Override
	public TSPTaboolator getTaboolator() {
		return (TSPTaboolator)taboolator;
	}
	
	@Override
	public void setEvaluator(Evaluator<TSPSolution, TSPSwapMove> evaluator) {
		this.evaluator = (TSPEvaluator)evaluator;
	}

	@Override
	public void setTaboolator(Taboolator<TSPSolution, TSPSwapMove> taboolator) {
		this.taboolator = (TSPTaboolator)taboolator;	
	}
	
	@Override
	public TSPFrequencyMemory getFrequencyMemory() {
		return (TSPFrequencyMemory)frequencyMemory;
	}

	@Override
	public void setFrequencyMemory(FrequencyMemory<TSPSolution, TSPSwapMove> frequencyMemory) {
		this.frequencyMemory = (TSPFrequencyMemory)frequencyMemory;
	}
	
	@Override
	public GenerationFabric<TSPSolution, TSPGeneration> getGenerationFabric() {
		return new TSPGenerationFabric();
	}


	
}