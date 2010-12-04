package taboosearch;

public class Solution extends core.Solution {

	private int[] tour;
	private Double fitness;
	
	public Solution(int[] initialTour) {
		this.tour = initialTour.clone();
	}
		
	public int get(int index) {
		return this.tour[index];
	}
	
	public int[] getTour() {
		return this.tour.clone();
	}
	
	public int length() {
		return this.tour.length - 1;
	}
	
	public double getFitness() {
		if (this.fitness == null) {
			Context c = Context.getInstance();
			this.fitness = c.e.evaluate(this);
		}
		return this.fitness;
	}
	
	public Solution clone() {		
		return new Solution(this.tour.clone());
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("[ " + this.tour[0]);
		
		for (int i = 1; i < tour.length; ++i)
			result.append(", " + (this.tour[i] + 1));

		result.append(" ] ");
		return result.toString();	
	}
	
}
