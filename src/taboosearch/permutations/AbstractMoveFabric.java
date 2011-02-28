package taboosearch.permutations;

public interface AbstractMoveFabric<S extends Solution, M extends Move<S>> {
	public M makeMove(int i, int j);
}
