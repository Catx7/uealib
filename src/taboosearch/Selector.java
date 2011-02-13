package taboosearch;

import java.util.List;
import java.util.TreeMap;

import taboosearch.exceptions.NotEvaluatedSolution;

import common.Evaluated;
import common.Pair;

public class Selector<S extends Solution, M extends Move<S>, G extends Generation<S>, C extends Context<S, M, G>> {
	private C context;
	private Evaluator<S, M> evaluator;
	private Taboolator<S, M> taboolator;
	private FrequencyMemory<S, M> frequencyMemory;
	private AdmissibilityChecker<S, M> admissibilityChecker;
	private EliteCandidateList<S, M> eliteList;
	
	public Selector(AdmissibilityChecker<S, M> admissibilityChecker, C context) {
		this.context = context;
		this.taboolator = context.getTaboolator();
		this.frequencyMemory = context.getFrequencyMemory();
		this.evaluator = context.getEvaluator();
		this.admissibilityChecker = admissibilityChecker;
		this.eliteList = context.eliteList;
	}
		
	public TreeMap<Double, M> getEvaluatedMoves(Pair<S, List<M>> boundMoves) {		
		S solution = boundMoves.getFirst();
		List<M>	moves = boundMoves.getSecond();
		double bestCostEver = context.bestSolutionEverCost;
		TreeMap<Double, M> qualities = new TreeMap<Double, M>();
		for (M move : moves) {
			if (admissibilityChecker.isAdmissible(solution, move, bestCostEver)) {
				double quality = evaluator.evaluateMove(solution, move);
				qualities.put(quality, move);
			}
		}
		return qualities;
	}
	
	public G keepTheBestSolutions(Pair<S, List<M>> boundMoves)  {	
		S currentSolution = boundMoves.getFirst();
		
		if (eliteList.needsToBeRebuilt()) {
			eliteList.rebuild(getEvaluatedMoves(boundMoves));
		}
		
		Evaluated<M> evaluatedMove = eliteList.getMove();
		M bestMove = evaluatedMove.getObject();
		double bestMoveCost = evaluatedMove.getCost();
		
		taboolator.tick(currentSolution, bestMove);
		eliteList.tick(currentSolution, context.bestSolutionEverCost);
		frequencyMemory.tick(currentSolution, bestMove);

		G result = context.getGenerationFabric().makeGeneration();
		S resultSolution = bestMove.operateOn(currentSolution);
		
		double resultSolutionCost;
		try {
			resultSolutionCost = currentSolution.getCost() + bestMoveCost;
		} catch (NotEvaluatedSolution e) {
			System.err.println(e.getMessage());
			resultSolutionCost = evaluator.evaluate(currentSolution) + bestMoveCost;
		}
		
		resultSolution.setCost(resultSolutionCost);
		result.add(resultSolution);
		return result;
	}

}
