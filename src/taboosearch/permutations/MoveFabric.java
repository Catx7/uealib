package taboosearch.permutations;

public interface MoveFabric<S extends Solution, M extends Move<S>> {
	public M makeMove(int i, int j);
}
