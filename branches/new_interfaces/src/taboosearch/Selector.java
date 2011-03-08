package taboosearch;

import java.util.List;
import java.util.TreeMap;

import taboosearch.exceptions.NotEvaluatedSolution;
import taboosearch.exceptions.UnsupportedMoveType;
import common.AbstractGenerationAndSolutionFabric;

import common.Evaluated;
import common.Pair;

public class Selector<S extends Solution, M extends Move<S>, G extends Generation<S>, C extends Context<S, M, G>> {
	private C context;
	private Evaluator<S, M> evaluator;
	private AdmissibilityChecker<S, M> admissibilityChecker;
	private EliteCandidateList<S, M> eliteList;
	private AbstractGenerationAndSolutionFabric<S, G> fabric;
	private List<? extends Tickable<S, M>> tickables;
	
	public Selector(
			Evaluator<S, M> evaluator,
			AdmissibilityChecker<S, M> admissibilityChecker,
			EliteCandidateList<S, M> eliteList,
			AbstractGenerationAndSolutionFabric<S, G> fabric,
			List<? extends Tickable<S, M>> tickables,
			C context) {
		this.context = context;
		this.evaluator = evaluator;
		this.admissibilityChecker = admissibilityChecker;
		this.eliteList = eliteList;
		this.fabric = fabric;
		this.tickables = tickables;
	}
		
	private TreeMap<Double, M> getEvaluatedMoves(Pair<S, List<M>> boundMoves)
							throws UnsupportedMoveType, NotEvaluatedSolution {
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
	
	public G keepTheBestSolutions(Pair<S, List<M>> boundMoves) throws UnsupportedMoveType, NotEvaluatedSolution  {	
		S currentSolution = boundMoves.getFirst();
				
		if (eliteList.needsToBeRebuilt()) {
			eliteList.rebuild(getEvaluatedMoves(boundMoves));
		}
		
		Evaluated<M> evaluatedMove = eliteList.getMove();
		M bestMove = evaluatedMove.getObject();
		
		S nextSolution = bestMove.operateOn(currentSolution);
		double nextSolutionCost = evaluator.evaluate(nextSolution);
		nextSolution.setCost(nextSolutionCost);
		
		context.setCurrentSolution(nextSolution, nextSolutionCost);
		for (Tickable<S, M> tickable : tickables)
			tickable.tick(currentSolution,
						  bestMove,
						  nextSolution,
						  nextSolutionCost);
		
		G result = fabric.makeGeneration();
		result.add(nextSolution);
		return result;
	}
}
