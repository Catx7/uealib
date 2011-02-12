package taboosearch.tsp;

import common.AbstractGenerationFabric;

public class TSPGenerationFabric extends AbstractGenerationFabric<TSPSolution, TSPGeneration> {

	@Override
	public TSPGeneration makeGeneration() {
		return new TSPGeneration();
	}

}
