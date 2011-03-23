package genetic;

public abstract class Solution<S extends Solution<S>> extends core.Solution {
	
	protected java.util.Random rand;
	
	protected Solution() {
		this.rand = new java.util.Random();
	}

	abstract public void doCrossover(S parent2);
	
	abstract public void mutate();

	abstract public String getRepresentation();
	
	public abstract S copy();
}