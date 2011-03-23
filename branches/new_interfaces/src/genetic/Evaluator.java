package genetic;

public abstract class Evaluator<S> implements java.util.Comparator<S> {
	public abstract double evaluate(S solution);

	public void makeFeasibleSolution(S solution) {
	}
}
