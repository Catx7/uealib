package usablefunctions;

class F3 implements Function {
	private static final double[][] domain = {
		{-10, 10}
	};

	@Override
	public double apply(double[] xs) {
		assert xs.length == 1;
		double x = xs[0];
		double result = 0;
		for (int i = 1; i <= 5; ++i) {
			result += i * Math.sin(i + 1) * x + i;
		}
		return result;
	}

	@Override
	public double[][] getDomain() {
		return domain;
	}

}
