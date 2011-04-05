package readers.items;

public class Item {
	
	private double utility;
	private double weight;
	
	public Item(double u, double w) {
		this.utility = u;
		this.weight = w;
	}
	
	public double getUtility() {
		return utility;
	}

	public double getWeight() {
		return weight;
	}



}
