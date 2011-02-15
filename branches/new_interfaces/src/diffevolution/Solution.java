package diffevolution;

public abstract class Solution<S extends Solution<S>> extends core.Solution {

	abstract public void doCrossover(S donor);
	
	abstract public String getRepresentation();
}