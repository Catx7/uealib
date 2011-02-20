package taboosearch;

public interface Evaluator<S extends Solution, M extends Move<S>> {
	public double evaluateMove(S solution, M move);
	public double evaluate(S solution, M move);
	public double evaluate(S solution);
}
