package taboosearch;

import java.util.List;

import common.Pair;

public abstract class Generator<S extends Solution,
								M extends Move<S>,
								G extends Generation<S>> {
	public abstract Pair<S, List<M>> getNext(G g);
}
