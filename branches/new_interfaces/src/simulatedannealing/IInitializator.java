package simulatedannealing;

import core.Solution;

public interface IInitializator<T extends Solution> {
	public T getInitialSolution();
}
