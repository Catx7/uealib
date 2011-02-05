package taboosearch;

public abstract class Evaluator<S extends Solution, M extends Move<S>> {
	abstract public double evaluateMove(S solution, M move);
	abstract public double evaluate(S solution, M move);
	abstract public double evaluate(S solution);
}
