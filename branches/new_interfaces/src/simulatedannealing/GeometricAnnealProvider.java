package simulatedannealing;

public class GeometricAnnealProvider implements IAnnealProvider{

	private double alpha;
	public GeometricAnnealProvider(double alpha) {
		this.alpha = alpha;
	}
	
	@Override
	public double anneal(double t) {
		return alpha*t;
	}

}
