package genetic;

public interface Evaluator<S> extends java.util.Comparator<S> {
	public double evaluate(S solution);
}
