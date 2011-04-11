package taboosearch.knapsack;

import java.util.ArrayList;
import java.util.Collection;
import common.Pair;
import taboosearch.exceptions.NotEvaluatedSolution;
import taboosearch.readers.KnapsackProblem;

public class KPGenerator implements taboosearch.Generator<KPSolution, KPMove> {
	private double[] weights;
	private double capacity;
	private int n;
	
	public KPGenerator(KnapsackProblem task) {
		this.weights = task.getWeights();
		this.n = this.weights.length;
		this.capacity = task.getCapacity();
	}
	
	private ArrayList<KPMove> getAllMoves(KPSolution solution) throws NotEvaluatedSolution {
		double currentWeight = 0;
		for (Integer item : solution) {
			currentWeight += weights[item];
		}
		
		ArrayList<KPMove> result = new ArrayList<KPMove>();

		for (int i = 0; i < n; ++i) {
			if (solution.contains(i)) {
				result.add(new KPMove(KPMove.Action.DROP, i));
			} else {
				if (currentWeight + weights[i] > capacity) {
					for (int j = 0; j < n; ++j) {
						if (j == i) continue;
						if (solution.contains(j) &&
							currentWeight + weights[i] - weights[j] <= capacity) {
							result.add(new KPMove(i, j));
						}			
					}
				} else {
					result.add(new KPMove(KPMove.Action.ADD, i));	
				}
			}
		}

		return result;	
	}
	@Override
	public Pair<KPSolution, Collection<KPMove>> getMoves(KPSolution solution) throws NotEvaluatedSolution {	
		return new Pair<KPSolution, Collection<KPMove>>(solution, getAllMoves(solution));
	}

}
