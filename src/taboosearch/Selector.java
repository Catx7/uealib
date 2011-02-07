package taboosearch;

import java.util.List;
import java.util.TreeMap;

import common.Evaluated;
import common.Pair;

public class Selector<S extends Solution,
					  M extends Move<S>,
					  G extends Generation<S>,
					  C extends Context<S, M, G>> {
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
		
	public TreeMap<Double, M> getEvaluatedMoves(S solution) {		
		TreeMap<Double, M> qualities = new TreeMap<Double, M>();
		List<M>	moves = context.staticMoves;
		double bestCostEver = context.bestSolutionEverCost;
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
			eliteList.rebuild(getEvaluatedMoves(currentSolution));
		}
		
		Evaluated<M> evaluatedMove = eliteList.getMove();
		M bestMove = evaluatedMove.getObject();
		double bestMoveCost = evaluatedMove.getCost();
		
		taboolator.tick(currentSolution, bestMove);
		eliteList.tick(currentSolution, context.bestSolutionEverCost);
		frequencyMemory.tick(currentSolution, (M)bestMove);

		G result = context.getGenerationFabric().makeGeneration();
		S resultSolution = bestMove.operateOn(currentSolution);
		
		double resultSolutionCost = currentSolution.getCost() + bestMoveCost;
		
		resultSolution.setCost(resultSolutionCost);
		result.add(resultSolution);
		return result;
	}

}
