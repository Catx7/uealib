package taboosearch;


/**
 * "Ленивое" решение. Содержит в себе начальное решение solution и изменение move,
 * которое приводит от solution к следующему решению newSolution.
 * SolutionDiff ведёт себя как newSolution.
 */
public class LazySolution extends core.Solution {
	
	private Solution solution;
	private Move move;
	private Double fitness;
	
	public LazySolution(Solution s, Move m) {
		this.solution = s;
		this.move = m;
	}
	
	public Solution getSolution() {
		return solution;
	}

	public Move getMove() {
		return move;
	}

	public double getFitness() {
		if (this.fitness == null) {
			Context c = Context.getInstance();
			this.fitness = c.e.evaluate(this);
		}
		return this.fitness;
	}
	
	public Solution castToSolution() {
		int[] tour = this.solution.getTour();
		int i = this.move.getI();
		int j = this.move.getJ();
		int tmp = tour[i];
		tour[i] = tour[j];
		tour[j] = tmp;
		return new Solution(tour);
	}

}
