package taboosearch.tsp;

import taboosearch.GenerationFabric;

public class TspGenerationFabric extends GenerationFabric<TspSolution, TspGeneration> {

	@Override
	public TspGeneration makeGeneration() {
		return new TspGeneration();
	}

}
