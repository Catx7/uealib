package taboosearch;

import taboosearch.exceptions.UnsupportedMoveType;

public interface Tickable<S extends Solution, M extends Move<S>> {
	public void tick(S currentSolution, M selectedMove, S nextSolution, double bestCostEver) throws UnsupportedMoveType;
}
