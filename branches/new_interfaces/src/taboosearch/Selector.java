package taboosearch;

import java.util.List;
import java.util.TreeMap;

import common.AbstractGenerationFabric;
import common.Evaluated;
import common.Pair;

public class Selector<S extends Solution, M extends Move<S>, G extends Generation<S>, C extends Context<S, M, G>> {
	private C context;
	private Evaluator<S, M> evaluator;
	private Taboolator<S, M> taboolator;
	private FrequencyMemory<S, M> frequencyMemory;
	private AdmissibilityChecker<S, M> admissibilityChecker;
	private EliteCandidateList<S, M> eliteList;
	private AbstractGenerationFabric<S, G> generationFabric;
	
	public Selector(
			Evaluator<S, M> evaluator,
			Taboolator<S, M> taboolator,
			FrequencyMemory<S, M> frequencyMemory,
			AdmissibilityChecker<S, M> admissibilityChecker,
			EliteCandidateList<S, M> eliteList,
			AbstractGenerationFabric<S, G> generationFabric,
			C context) {
		this.context = context;
		this.taboolator = taboolator;
		this.frequencyMemory = frequencyMemory;
		this.evaluator = evaluator;
		this.admissibilityChecker = admissibilityChecker;
		this.eliteList = eliteList;
		this.generationFabric = generationFabric;
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

		S resultSolution = bestMove.operateOn(currentSolution);
		double resultSolutionCost = evaluator.evaluate(resultSolution);
		resultSolution.setCost(resultSolutionCost);
		
		context.setCurrentSolution(resultSolution, resultSolutionCost);
		taboolator.tick(currentSolution, bestMove);
		frequencyMemory.tick(currentSolution, bestMove);
		eliteList.tick(resultSolution, context.bestSolutionEverCost);	
		
		G result = generationFabric.makeGeneration();
		result.add(resultSolution);
		return result;
	}

}
