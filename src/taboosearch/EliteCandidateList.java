package taboosearch;

import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Map.Entry;

import taboosearch.exceptions.NotEvaluatedSolution;
import taboosearch.exceptions.UnsupportedMoveType;

import common.Evaluated;

public class EliteCandidateList<S extends Solution, M extends Move<S>> 
		implements Tickable<S, M> {
	private TreeMap<Double, M> evaluatedMoves;
	private double qualityThreshold;
	private int size;
	
	private Evaluator<S, M> evaluator;
	private AdmissibilityChecker<S, M> admissibilityChecker;
	
	public EliteCandidateList(int size, AdmissibilityChecker<S, M> admissibilityChecker, Evaluator<S, M> evaluator) {
		this.size = size;
		this.evaluatedMoves = new TreeMap<Double, M>();
		this.evaluator = evaluator;
		this.admissibilityChecker = admissibilityChecker;
	}
	
	public boolean needsToBeRebuilt() {
		return evaluatedMoves.size() == 0;
	}
	
	public void rebuild(TreeMap<Double, M> evaluatedAllMoves) {
		evaluatedMoves.clear();
		for (int times = 0; times < size; ++times) {
			Entry<Double, M> e = evaluatedAllMoves.pollFirstEntry();			
			evaluatedMoves.put(e.getKey(), e.getValue());
		}
		qualityThreshold = evaluatedMoves.lastKey();
	}
	
	public Evaluated<M> getMove() {
		Entry<Double, M> e = evaluatedMoves.pollFirstEntry();
		return new Evaluated<M>(e.getValue(), e.getKey());
	}
	
	public void tick(final S currentSolution /* unused */, final M selectedMove /* unused */,
			final S nextSolution, double bestCostEver) throws UnsupportedMoveType, NotEvaluatedSolution {
		Collection<M> moves = new LinkedList<M>(evaluatedMoves.values());
		evaluatedMoves.clear();
		
		for (M move : moves) {
			if (admissibilityChecker.isAdmissible(nextSolution, move, bestCostEver)) {
				double quality = evaluator.evaluateMove(nextSolution, move);
				if (quality < qualityThreshold)
					evaluatedMoves.put(quality, move);
			}
		}
	}
}
