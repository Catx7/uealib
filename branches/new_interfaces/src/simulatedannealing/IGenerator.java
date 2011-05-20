package simulatedannealing;

import core.Solution;

public interface IGenerator<T extends Solution> {
	public T getNext(T currentSolution);
}
