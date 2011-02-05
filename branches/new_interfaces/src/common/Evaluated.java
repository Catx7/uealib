package common;

public class Evaluated<O> {
	private O object;
	private double cost;
	
	public Evaluated(O object, double cost) {
		this.object = object;
		this.cost = cost;
	}
	
	public O getObject() {
		return object;
	}
	
	public double getCost() {
		return cost;
	}
}
