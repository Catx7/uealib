package taboosearch;

public abstract class Generator<S extends Solution, G extends Generation<S>> implements core.Generator<G> {
	
	@Override
	public abstract G getNext(G g);

}
