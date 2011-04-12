package simulatedannealing.continous;

import core.Solution;
import functions.Functions;
import simulatedannealing.Evaluator;
import usablefunctions.Function;

public class FuncEvaluator implements Evaluator {

	private Function func;
	public FuncEvaluator(Function func) {
		this.func = func;
	}

	@Override
	public int compare(Solution arg0, Solution arg1) {
		return Double.compare(evaluate(arg1), evaluate(arg0));
	}

	@Override
	public double evaluate(Solution s) {
		Point a = (Point) s;
		return func.apply(a.coords);
	}

}
