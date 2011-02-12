package taboosearch.permutations;

public interface SolutionFabric<S extends Solution> extends common.AbstractSolutionFabric<S> {
	public S makeSolution();
	public S makeSolution(int[] permutation);
}
