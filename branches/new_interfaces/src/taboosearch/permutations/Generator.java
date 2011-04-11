package taboosearch.permutations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import common.Pair;

public class Generator<S extends Solution, M extends Move<S>>
			implements taboosearch.Generator<S, M> {
	private AbstractMoveFabric<? extends S, ? extends M> moveFabric;
	private List<M> moves;
	
	public Generator(int n, AbstractMoveFabric<? extends S, ? extends M> moveFabric) {	
		this.moveFabric = moveFabric;
		this.moves = getMoves(n);
	}
		
	private ArrayList<M> getMoves(int n) {
		ArrayList<M> result = new ArrayList<M>((n - 2) * (n - 1) / 2);
		for (int i = 1; i < n - 1; ++i) {
			for (int j = i + 1; j < n; ++j) {
				result.add(moveFabric.makeMove(i, j));
			}
		}
		return result;	
	}

	public Pair<S, Collection<M>> getMoves(S solution) {
		return new Pair<S, Collection<M>>(solution, moves);
	}
}
