package pso;

public abstract interface Generator<G extends Generation<S>, S extends Solution<S>> {
	public G getNextGeneration(G g1, G g2, S s);
}
