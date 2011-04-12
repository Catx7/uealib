package usablefunctions;

public class Brown3 implements Function {
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
		
		double sum = 0;
		
		for(int i = 0;i < 19; ++i) {
			sum += Math.pow(xs[i]*xs[i], xs[i+1]*xs[i+1]+1) +
				   Math.pow(xs[i+1]*xs[i+1], xs[i]*xs[i]+1);
		}
		
		return sum;
	}

	@Override
	public double[][] getDomain() {
		return domain;
	}
		

}
