package taboosearch;

import java.util.List;

import taboosearch.exceptions.NotEvaluatedSolution;

import common.Pair;

public abstract interface Generator<S extends Solution, M extends Move<S>, G extends Generation<S>> {
	public Pair<S, List<M>> getNext(G g) throws NotEvaluatedSolution;
}
