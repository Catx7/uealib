package taboosearch.tsp;

import taboosearch.GenerationFabric;

public class TSPGenerationFabric extends GenerationFabric<TSPSolution, TSPGeneration> {

	@Override
	public TSPGeneration makeGeneration() {
		return new TSPGeneration();
	}

}
