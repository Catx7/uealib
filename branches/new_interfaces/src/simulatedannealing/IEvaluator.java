package simulatedannealing;

import java.util.Comparator;

import core.Solution;

public interface IEvaluator<T extends Solution> extends Comparator<T> {
	double evaluate(T s);

}
