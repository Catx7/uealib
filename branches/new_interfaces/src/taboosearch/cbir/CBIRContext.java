package taboosearch.cbir;

import java.util.ArrayList;

import taboosearch.Context;
import taboosearch.Evaluator;
import taboosearch.FrequencyMemory;
import taboosearch.GenerationFabric;
import taboosearch.Taboolator;

public class CBIRContext extends Context<CBIRSolution, CBIRSwapMove, CBIRGeneration> {
	
	public CBIRContext(Evaluator<CBIRSolution, CBIRSwapMove> evaluator,
					  Taboolator<CBIRSolution, CBIRSwapMove> taboolator,
					  FrequencyMemory<CBIRSolution, CBIRSwapMove> frequencyMemory) {
		super(evaluator, taboolator, frequencyMemory);
		this.eliteList = new CBIREliteCandidateList(6,
				new CBIRAdmissibilityChecker(evaluator, taboolator, frequencyMemory), this);
		this.staticMoves = new ArrayList<CBIRSwapMove>();
	}

	@Override
	public CBIREvaluator getEvaluator() {
		return (CBIREvaluator)evaluator;
	}
	
	@Override
	public CBIRTaboolator getTaboolator() {
		return (CBIRTaboolator)taboolator;
	}
	
	@Override
	public void setEvaluator(Evaluator<CBIRSolution, CBIRSwapMove> evaluator) {
		this.evaluator = (CBIREvaluator)evaluator;
	}

	@Override
	public void setTaboolator(Taboolator<CBIRSolution, CBIRSwapMove> taboolator) {
		this.taboolator = (CBIRTaboolator)taboolator;	
	}
	
	@Override
	public CBIRFrequencyMemory getFrequencyMemory() {
		return (CBIRFrequencyMemory)frequencyMemory;
	}

	@Override
	public void setFrequencyMemory(FrequencyMemory<CBIRSolution, CBIRSwapMove> frequencyMemory) {
		this.frequencyMemory = (CBIRFrequencyMemory)frequencyMemory;
	}
	
	@Override
	public GenerationFabric<CBIRSolution, CBIRGeneration> getGenerationFabric() {
		return new CBIRGenerationFabric();
	}


	
}