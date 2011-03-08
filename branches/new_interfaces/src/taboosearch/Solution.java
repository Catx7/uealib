package taboosearch;

import taboosearch.exceptions.NotEvaluatedSolution;

public abstract class Solution extends core.Solution {
	protected Double cost;
	
	abstract public String getStringRepresentation();

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getCost() throws NotEvaluatedSolution {
		if (cost == null) {
			throw new NotEvaluatedSolution();
		}
		return cost;
	}
}
