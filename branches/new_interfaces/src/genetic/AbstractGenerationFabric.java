package genetic;

public interface AbstractGenerationFabric<G extends Generation<?>, C extends core.Context> {
	G makeGeneration(C context);
}
