package usablefunctions;

public class Hartmann implements Function {
	public static final double[] c = { 1.0, 1.2, 3.0, 3.2 };
	
	public static final double[][] a = { { 3.0, 10.0, 30.0 },
										 { 0.1, 10.0, 35.0 },
										 { 3.0, 10.0, 30.0 },
										 { 0.1, 10.0, 35.0 } };
	
	public static final double[][] p = { { 0.3689, 0.117,  0.2673  },
										 { 0.4699, 0.4387, 0.747   },
										 { 0.1091, 0.8732, 0.5547  },
										 { 0.0382, 0.5743, 0.8828} };
	
	private static final double[][] domain = {
		{ 0, 1 },
		{ 0, 1 },
		{ 0, 1 },
	};
	
	@Override
	public double apply(double[] xs) {
		assert xs.length == 3;
		double result = 0;
		for (int i = 0; i < 4; ++i){
			double sum = 0;
			for (int j = 0; j < 3; ++j){
				sum += a[i][j] * Math.pow(xs[j] - p[i][j], 2);
			}
			result += c[i] * Math.exp(-sum);
		}
		return -result;
	}

	@Override
	public double[][] getDomain() {
		return domain;
	}
	
}
