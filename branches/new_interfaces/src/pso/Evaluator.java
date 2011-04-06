package pso;

public abstract class Evaluator<S extends pso.Solution<S>> {
	abstract public double evaluate(S solution);
}
