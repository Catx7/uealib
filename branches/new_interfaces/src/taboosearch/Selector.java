package taboosearch;

import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

import taboosearch.exceptions.NotEvaluatedSolution;
import taboosearch.exceptions.UnsupportedMoveType;

import common.Evaluated;
import common.Pair;

public class Selector<S extends Solution, M extends Move<S>, C extends Context<S, M>>
			implements core.alternative.Selector<S, M> {
	private C context;
	private Evaluator<S, M> evaluator;
	private AdmissibilityChecker<S, M> admissibilityChecker;
	private EliteCandidateList<S, M> eliteList;
	private List<? extends Tickable<S, M>> tickables;
	
	public Selector(
			Evaluator<S, M> evaluator,
			AdmissibilityChecker<S, M> admissibilityChecker,
			EliteCandidateList<S, M> eliteList,
			List<? extends Tickable<S, M>> tickables,
			C context) {
		this.context = context;
		this.evaluator = evaluator;
		this.admissibilityChecker = admissibilityChecker;
		this.eliteList = eliteList;
		this.tickables = tickables;
	}
		
	private TreeMap<Double, M> getEvaluatedMoves(Pair<S, Collection<M>> boundMoves)
							throws UnsupportedMoveType, NotEvaluatedSolution {
		S solution = boundMoves.getFirst();
		Collection<M> moves = boundMoves.getSecond();
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
	
	public S getBestSolution(Pair<S, Collection<M>> boundMoves) {	
		S currentSolution = boundMoves.getFirst();
				
		if (eliteList.needsToBeRebuilt()) {
			try {
				eliteList.rebuild(getEvaluatedMoves(boundMoves));
			} catch (UnsupportedMoveType e) {
				e.printStackTrace();
			} catch (NotEvaluatedSolution e) {
				e.printStackTrace();
			}
		}
		
		Evaluated<M> evaluatedMove = eliteList.getMove();
		M bestMove = evaluatedMove.getObject();
		
		S nextSolution = bestMove.operateOn(currentSolution);
		double nextSolutionCost = evaluator.evaluate(nextSolution);
		nextSolution.setCost(nextSolutionCost);
		
		context.setCurrentSolution(nextSolution, nextSolutionCost);
		for (Tickable<S, M> tickable : tickables)
			try {
				tickable.tick(currentSolution,
							  bestMove,
							  nextSolution,
							  nextSolutionCost);
			} catch (UnsupportedMoveType e) {
				e.printStackTrace();
			} catch (NotEvaluatedSolution e) {
				e.printStackTrace();
			}
		
		return nextSolution;
	}

}
