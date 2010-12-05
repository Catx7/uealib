package simulatedannealing;

import java.util.Comparator;

import core.Solution;

public interface Evaluator extends Comparator<Solution> {
	double evaluate(Solution s);
	
}
