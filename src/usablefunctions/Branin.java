package usablefunctions;

public class Branin implements Function {
	private static final double[][] domain = {
		{ -Double.MAX_VALUE, Double.MAX_VALUE },
		{ -Double.MAX_VALUE, Double.MAX_VALUE }
	};
	
	private static final double a = 1.0;
	private static final double b = 5.1 / (4 * Math.pow(Math.PI, 2));
	private static final double c = 5.0 / Math.PI;
	private static final double d = 6.0;
	private static final double h = 10.0;
	private static final double g = 1.0 / (8 * Math.PI);

	@Override
	public double apply(double[] xs) {
		assert xs.length == 2;
		double x = xs[0], y = xs[1];
		return a * (y - b * x * x + c * x -d) * (y - b * x * x + c * x - d) + h * (1 - g) * Math.cos(x) + h;
	}

	@Override
	public double[][] getDomain() {
		return domain;
	}

}
