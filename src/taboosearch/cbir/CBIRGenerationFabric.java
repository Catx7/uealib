package taboosearch.cbir;

import common.AbstractGenerationFabric;

public class CBIRGenerationFabric extends AbstractGenerationFabric<CBIRSolution, CBIRGeneration> {

	@Override
	public CBIRGeneration makeGeneration() {
		return new CBIRGeneration();
	}

}
