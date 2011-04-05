package diffevolution;

public abstract class Evaluator<S extends diffevolution.Solution> {
	abstract public double evaluate(S solution);
}
