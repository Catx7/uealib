package taboosearch;

public abstract class FrequencyMemory<S extends Solution, M extends Move<S>> {
	abstract public void tick(S solution, M move);
	abstract public double getPenalty(S solution, M move);
}
