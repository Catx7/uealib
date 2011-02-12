package taboosearch.readers;

public class Feature implements Comparable<Feature> { // immutable
	private double[] components;
	private String name;
	
	public Feature(String name, double[] components) {
		assert components.length == 112;
		this.components = components;
		this.name = name;
	}
	
	public double get(int idx) {
		return components[idx];
	}
	
	public String getName() {
		return name;
	}
	
	public int compareTo(Feature that) {
		return this.name.compareTo(that.name);
	}
	
	public Feature sub(Feature that) {
		double[] components = new double[112];
		for (int i = 0; i < 112; ++i) {
			components[i] = this.get(i) - that.get(i);
		}
		return new Feature("", components);
	}

	public Feature mul(double c) {
		double[] components = new double[112];
		for (int i = 0; i < 112; ++i) {
			components[i] = c * this.get(i);
		}
		return new Feature("", components);
	}
	
	public double norm() {
		double normSquare = 0;
		for (int i = 0; i < 112; ++i) {
			normSquare += Math.pow(this.get(i), 2);
		}
		return Math.sqrt(normSquare);
	}
	
	public double distanceTo(Feature that) {
		double distanceSquare = 0;
		for (int i = 0; i < 112; ++i) {
			distanceSquare += Math.pow(this.get(i) - that.get(i), 2);
		}
		return Math.sqrt(distanceSquare);
	}
}
