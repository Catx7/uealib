package usablefunctions;

public class Goldprice implements Function {
	private static final double[][] domain = {
		{ -Double.MAX_VALUE, Double.MAX_VALUE },
		{ -Double.MAX_VALUE, Double.MAX_VALUE }
	};
	
	@Override
	public double apply(double[] xs) {
		assert xs.length == 2;
		double x = xs[0], y = xs[1];
		return 	(1 + Math.pow(x + y + 1, 2) *
					(19 - 14 * x + 3 * x * x - 14 * y + 6 * x * y + 3 * y * y)) *
				(30 + Math.pow((2 * x - 3 * y), 2) *
					(18 - 32 * x + 12 * x * x + 48 * y - 36 * x * y + 27 * y * y));
	}

	@Override
	public double[][] getDomain() {
		return domain;
	}

}
