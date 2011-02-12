package taboosearch.tsp;

import common.AbstractGenerationFabric;
import taboosearch.Context;
import taboosearch.EliteCandidateList;
import taboosearch.Evaluator;
import taboosearch.FrequencyMemory;
import taboosearch.Taboolator;

public class TSPContext extends Context<TSPSolution, TSPSwapMove, TSPGeneration> {

	public TSPContext(
			Evaluator<TSPSolution, TSPSwapMove> evaluator,
			Taboolator<TSPSolution, TSPSwapMove> taboolator,
			FrequencyMemory<TSPSolution, TSPSwapMove> frequencyMemory,
			AbstractGenerationFabric<TSPSolution, TSPGeneration> generationFabric,
			EliteCandidateList<TSPSolution, TSPSwapMove> eliteList) {
		super(evaluator, taboolator, frequencyMemory, generationFabric, eliteList);
	}

}