package taboosearch;

import taboosearch.exceptions.NotEvaluatedSolution;

public abstract class Solution extends core.Solution {
	abstract public String getStringRepresentation();
	abstract public void setCost(double cost);
	abstract public double getCost() throws NotEvaluatedSolution;
}
