package taboosearch;

import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Map.Entry;

import common.Evaluated;

public class EliteCandidateList<S extends Solution,
								M extends Move<S>,
								G extends Generation<S>,
								C extends Context<S, M, G>> {
	private TreeMap<Double, M> evaluatedMoves;
	private double qualityThreshold;
	private boolean rebuildNeeded;
	private int size;
	private C context;
	private AdmissibleChecker<S, M, G, C> admissibleChecker;
	
	public EliteCandidateList(int size, AdmissibleChecker<S, M, G, C> admissibleChecker, C context) {
		this.context = context;
		this.evaluatedMoves = new TreeMap<Double, M>();
		this.rebuildNeeded = true;
		this.size = size;
		this.admissibleChecker = admissibleChecker;
	}
	
	public boolean needsToBeRebuilt() {
		return rebuildNeeded || evaluatedMoves.size() == 0;
	}
	
	public void rebuild(TreeMap<Double, M> qualities) {
		rebuildNeeded = false;
		qualityThreshold = -Double.MAX_VALUE;
		evaluatedMoves.clear();
		
		for (int times = 0; times < size; ++times) {
			Entry<Double, M> e = qualities.pollFirstEntry();
			double quality = e.getKey();
			
			evaluatedMoves.put(quality, e.getValue());
			if (quality > qualityThreshold) {
				qualityThreshold = quality;
			}
		}
	}
	
	public Evaluated<M> getMove() {
		Entry<Double, M> e = evaluatedMoves.pollFirstEntry();
		return new Evaluated<M>(e.getValue(), e.getKey());
	}
	
	public Collection<M> getMoves() {
		return evaluatedMoves.values();
	}
	
	public void tick(S solution) {
		Evaluator<S, M> evaluator = context.getEvaluator();		
		Collection<M> moves = new LinkedList<M>(evaluatedMoves.values());
		
		evaluatedMoves.clear();
		rebuildNeeded = true;
		
		for (M move : moves) {
			boolean isAdded = false;
			if (admissibleChecker.isAdmissible(solution, move)) {
				double quality = evaluator.evaluateMove(solution, move);
				
				if (quality < qualityThreshold) {
					evaluatedMoves.put(quality, move);
					isAdded = true;
				}
			}
			rebuildNeeded &= !isAdded;
		}
	}
}
