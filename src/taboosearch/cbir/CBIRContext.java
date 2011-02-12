package taboosearch.cbir;


import common.AbstractGenerationFabric;
import taboosearch.Context;
import taboosearch.EliteCandidateList;
import taboosearch.Evaluator;
import taboosearch.FrequencyMemory;
import taboosearch.Taboolator;

public class CBIRContext extends Context<CBIRSolution, CBIRSwapMove, CBIRGeneration> {

	public CBIRContext(
			Evaluator<CBIRSolution, CBIRSwapMove> evaluator,
			Taboolator<CBIRSolution, CBIRSwapMove> taboolator,
			FrequencyMemory<CBIRSolution, CBIRSwapMove> frequencyMemory,
			AbstractGenerationFabric<CBIRSolution, CBIRGeneration> generationFabric,
			EliteCandidateList<CBIRSolution, CBIRSwapMove> eliteList) {
		super(evaluator, taboolator, frequencyMemory, generationFabric, eliteList);
	}
	
}