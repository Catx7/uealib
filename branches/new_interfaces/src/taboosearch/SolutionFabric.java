package taboosearch;

public interface SolutionFabric<S extends Solution> {
	abstract public S makeSolution();
}
