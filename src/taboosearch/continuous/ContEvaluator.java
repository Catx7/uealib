package taboosearch.continuous;

import taboosearch.Evaluator;
import taboosearch.exceptions.NotEvaluatedSolution;
import taboosearch.exceptions.UnsupportedMoveType;
import taboosearch.knapsack.KPMove;
import taboosearch.knapsack.KPSolution;

public class ContEvaluator extends Evaluator<ContSolution, ContMove> {

	private double function(double x, double y) {
		
		double sum1 = 0;
		for (int i = 1; i < 6; ++i) {
			sum1 += i * Math.cos((i + 1) * x + i);
		}
		
		double sum2 = 0;
		for (int i = 1; i < 6; ++i) {
			sum2 += i * Math.cos((i + 1) * y + i);
		}
		return sum1 * sum2 + 0.5 * (Math.pow(x + 1.42513, 2) + Math.pow(y + 0.80032, 2));


		//return (1 + Math.pow(x + y + 1, 2) * (19 - 14 * x + 3 * x * x - 14 * y + 6 * x * y + 3 * y * y)) *
		// (30 + Math.pow((2 * x - 3 * y), 2) * (18 - 32 * x + 12 * x * x + 48 * y - 36 * x * y + 27 * y * y));

		//return -Math.sin(x*x + y*y)/(x*x + y*y);
	}
	@Override
	public double evaluate(ContSolution solution, ContMove move)
			throws UnsupportedMoveType, NotEvaluatedSolution {
		return function(solution.getCoord(0) + move.deltaX, solution.getCoord(1) + move.deltaY);
	}

	@Override
	public double evaluate(ContSolution solution) {
		return function(solution.getCoord(0), solution.getCoord(1));
	}


}
