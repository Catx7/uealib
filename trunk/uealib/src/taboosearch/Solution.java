package taboosearch;

public class Solution extends core.Solution {

	private int[] tour;
	
	public Solution(int[] initialTour) {
		this.tour = initialTour.clone();
	}
		
	public int get(int index) {
		return this.tour[index];
	}
	
	public double getFitness() {
		Context c = Context.getInstance();
		return c.e.evaluate(this);
		//return this.tour.length;
	}
	
	public Solution clone() {		
		return new Solution(this.tour.clone());
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("[ " + this.tour[0]);
		
		for (int i = 1; i < tour.length; ++i)
			result.append(", " + this.tour[i]);

		result.append(" ] ");
		return result.toString();	
	}
	
}
