package taboosearch.cbir;

import taboosearch.GenerationFabric;

public class CBIRGenerationFabric extends GenerationFabric<CBIRSolution, CBIRGeneration> {

	@Override
	public CBIRGeneration makeGeneration() {
		return new CBIRGeneration();
	}

}
