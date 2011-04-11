package core.alternative;

import java.util.Collection;

import common.Pair;
import core.Solution;

public interface Selector<S extends Solution, M extends Move<S>> {
	public S getBestSolution(Pair<S, Collection<M>> boundMoves);
}
