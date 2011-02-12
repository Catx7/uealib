package taboosearch.permutations;

public interface SolutionFabric<S extends Solution> extends taboosearch.SolutionFabric<S> {
	public S makeSolution();
	public S makeSolution(int[] permutation);
}
