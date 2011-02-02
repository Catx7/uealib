package taboosearch;

public abstract class Evaluator<S extends Solution> {
		
	abstract public double evaluate(S solution);

}
