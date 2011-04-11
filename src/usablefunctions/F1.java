package usablefunctions;

public class F1 implements Function {
	private static final double[][] domain = {
		{0, 1}
	};

	@Override
	public double apply(double[] xs) {
		assert xs.length == 1;
		double x = xs[0];
		return 2 * Math.pow(x - 0.75, 2) + Math.sin(5 * Math.PI * x + 0.4 * Math.PI) - 0.125;
	}

	@Override
	public double[][] getDomain() {
		return domain;
	}
}
