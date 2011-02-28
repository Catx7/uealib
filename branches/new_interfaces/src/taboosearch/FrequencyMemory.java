package taboosearch;

public abstract class FrequencyMemory<S extends Solution, M extends Move<S>>
				implements Tickable<S, M> {
	abstract public double getPenalty(S solution, M move);
}
