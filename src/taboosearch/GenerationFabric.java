package taboosearch;

public abstract class GenerationFabric<S extends Solution, G extends Generation<S>> {
	
	abstract public G makeGeneration();

}