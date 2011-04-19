package usablefunctions;

public class Brown1 implements Function {
	private static final double[][] domain = initializeDomain();
	
	private static double[][] initializeDomain() {
		double[][] res = new double[20][2];
		for (int i = 0; i < res.length; i++) {
			res[i][0] = -1; 
			res[i][1] = 4;
		}
		return res;
	}
	
	@Override
	public double apply(double[] xs) {
		assert xs.length == 20;
		
		double sum1 = 0;
		double sum2 = 0;
		
		for(int i = 0;i < 19; ++i) {
			sum1 += (xs[i] - 3);
			sum2 += Math.pow(10, -3)*Math.pow(xs[i]-3,2) - (xs[i] - xs[i+1]) +
					Math.pow(Math.E, 20*(xs[i] - xs[i+1]));
		}
		
		return Math.pow(sum1,2) + sum2;
	}

	@Override
	public double[][] getDomain() {
		return domain;
	}
		

}
