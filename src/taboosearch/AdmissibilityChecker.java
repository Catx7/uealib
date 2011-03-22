package taboosearch;

import taboosearch.exceptions.NotEvaluatedSolution;
import taboosearch.exceptions.UnsupportedMoveType;

public class AdmissibilityChecker<S extends Solution, M extends Move<S>> {
	private Evaluator<S, M> evaluator;
	private Taboolator<S, M> taboolator;
	
	public AdmissibilityChecker(Evaluator<S, M> evaluator, Taboolator<S, M> taboolator) {
		this.evaluator = evaluator;
		this.taboolator = taboolator;
	}
	
	/**
	 * Проверяет, допустим ли ход для текущего решения.
	 * @param solution Решение, относительно которого будет проверяться допустимость хода.
	 * @param move Ход.
	 * @param bestCostEver Стоимость наилучшего решения, которое было посещено в процессе поиска.
	 * Используется для проверки aspiration criteria.  
	 * @return true, если ход допустим, и false, если недопустим
	 */
	public boolean isAdmissible(S solution, M move, double bestCostEver)
					throws UnsupportedMoveType, NotEvaluatedSolution {	
		if (!taboolator.isTabu(solution, move)) {
			return true;
		} else {
			double solutionCost = evaluator.evaluate(solution, move);
			return (solutionCost < bestCostEver); // aspiration criteria
		}
	}
	
	public boolean isAdmissible(S solution, M move) throws UnsupportedMoveType, NotEvaluatedSolution {	
		if (!taboolator.isTabu(solution, move)) {
			//System.out.println("TABOO" + evaluator.evaluate(solution, move));
			return true;
		}
		return false;
	}
}
