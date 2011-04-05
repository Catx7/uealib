package diffevolution;

public interface AbstractCrossoverFabric<S extends Solution> {
	public void doCrossover(S probe, S donor);
}
