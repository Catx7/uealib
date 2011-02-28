package taboosearch;

public abstract class Initializator<S extends Solution, G extends Generation<S>> implements core.Initializator<G> {
	public abstract G getInitialGeneration(int i);
}
