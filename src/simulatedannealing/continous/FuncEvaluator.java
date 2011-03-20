package simulatedannealing.continous;

import core.Solution;
import simulatedannealing.Evaluator;

public class FuncEvaluator implements Evaluator {

	private static double func(double x, double y) {
		return Math.sin(x * x + y * y) / (x * x + y * y);
	}

	@Override
	public int compare(Solution arg0, Solution arg1) {
		return Double.compare(evaluate(arg0), evaluate(arg1));
	}

	@Override
	public double evaluate(Solution s) {
		Point a = (Point) s;
		return func(a.x, a.y);
	}

}
