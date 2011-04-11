package taboosearch;

public abstract class Initializator<S extends Solution> implements core.alternative.Initializator<S> {
	public abstract S getInitialSolution(int seed);
	public S getInitialSolution() {
		return getInitialSolution(0);
	}
}
