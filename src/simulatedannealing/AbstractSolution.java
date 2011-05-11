package simulatedannealing;

import core.Solution;

public abstract class AbstractSolution extends Solution{
	private Double fitness;
	
	public AbstractSolution() {
		fitness = null;
	}
 	
	public Double getFitness() {
		return fitness;
	}

	public void setFitness(Double fitness) {
		if(this.fitness != null) 
			return;
		this.fitness = fitness;
	}
}
