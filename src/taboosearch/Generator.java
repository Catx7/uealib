package taboosearch;

import java.util.Collection;
import taboosearch.exceptions.NotEvaluatedSolution;
import common.Pair;

public abstract interface Generator<S extends Solution, M extends Move<S>> {
	public Pair<S, Collection<M>> getMoves(S solution) throws NotEvaluatedSolution;
}
