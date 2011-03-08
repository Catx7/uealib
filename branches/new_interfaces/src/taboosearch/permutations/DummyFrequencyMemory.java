package taboosearch.permutations;


public class DummyFrequencyMemory<S extends Solution, M extends Move<S>>
			extends FrequencyMemory<S, M> {
	public DummyFrequencyMemory(int n, double diversificationCoef) {
		super(n, diversificationCoef);
	}

	@Override
	public double getPenalty(S solution, M move) {
		return 0;
	}
	
	@Override
	public void tick(S currentSolution, M selectedMove, S nextSolution, double bestCostEver) {
	}
}
