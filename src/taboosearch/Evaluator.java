package taboosearch;

import taboosearch.exceptions.NotEvaluatedSolution;
import taboosearch.exceptions.UnsupportedMoveType;

public abstract class Evaluator<S extends Solution, M extends Move<S>> {
	public abstract double evaluate(S solution, M move) throws UnsupportedMoveType, NotEvaluatedSolution;
	public abstract double evaluate(S solution);
	
	public double evaluateMove(S solution, M move) throws UnsupportedMoveType, NotEvaluatedSolution {
		double cost = solution.getCost();
		return evaluate(solution, move) - cost;
	}
}
