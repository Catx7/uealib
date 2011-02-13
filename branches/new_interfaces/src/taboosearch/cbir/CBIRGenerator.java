package taboosearch.cbir;

public class CBIRGenerator
	extends taboosearch.permutations.Generator<CBIRSolution, CBIRSwapMove, CBIRGeneration> {

	public CBIRGenerator(int n) {
		super(n);
	}

	@Override
	protected CBIRSwapMove makeMove(int i, int j) {
		return new CBIRSwapMove(i, j);
	}

}
