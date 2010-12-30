package genetic;

public abstract class Solution<S extends Solution<S>> extends core.Solution<S> {

	abstract public void doCrossover(S parent2);
	
	abstract public void mutate();

	abstract public String getRepresentation();
}