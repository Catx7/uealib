package usablefunctions;

public class Shubert1 implements Function {
	private static final double[][] domain = {
		{ -10, Double.MAX_VALUE },
		{ -Double.MAX_VALUE, 10 },
	};
	
	@Override
	public double apply(double[] xs) {
		assert xs.length == 2;
		double x = xs[0], y = xs[1];
		
		double sum1 = 0;
		for (int i = 1; i < 6; ++i) {
			sum1 += i * Math.cos((i + 1) * x + i);
		}
		
		double sum2 = 0;
		for (int i = 1; i < 6; ++i) {
			sum2 += i * Math.cos((i + 1) * y + i);
		}
		
		return sum1 * sum2 + 0.5 * (
			Math.pow(x + 1.42513, 2) + Math.pow(y + 0.80032, 2)
		);
	}

	@Override
	public double[][] getDomain() {
		return domain;
	}
}