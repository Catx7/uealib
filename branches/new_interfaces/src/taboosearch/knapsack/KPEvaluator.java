package taboosearch.knapsack;

import taboosearch.Evaluator;
import taboosearch.exceptions.NotEvaluatedSolution;
import taboosearch.exceptions.UnsupportedMoveType;
import taboosearch.readers.KnapsackProblem;

public class KPEvaluator implements Evaluator<KPSolution, KPMove> {
	private double[] values;
	
	public KPEvaluator(KnapsackProblem task) {
		this.values = task.getValues();
	}
	
	public double evaluate(KPSolution solution) {
		double value = 0;
		for (Integer i : solution.items) {
			value += values[i];
		}
		return value;
	}
	
	public double evaluate(KPSolution solution, KPMove move) throws UnsupportedMoveType, NotEvaluatedSolution {
		Integer addedItem = move.getBeingAddedItem();
		Integer droppedItem = move.getBeingDroppedItem();
		
		double value = solution.getCost();
		
		if (addedItem != null)
			value += values[addedItem];
		if (droppedItem != null)
			value -= values[droppedItem];
		
		return value;
	}
	
	public double evaluateMove(KPSolution solution, KPMove move) throws UnsupportedMoveType, NotEvaluatedSolution {
		double cost = solution.getCost();
		return evaluate(solution, move) - cost;
	}
}
