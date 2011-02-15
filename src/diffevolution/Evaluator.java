package diffevolution;

public abstract class Evaluator<S extends diffevolution.Solution<S>> {
	abstract public double evaluate(S solution);
}
