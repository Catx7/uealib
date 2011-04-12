package usablefunctions;

public class F10n implements Function {
	private static final double[][] domain = initializeDomain();
	
	private static double[][] initializeDomain() {
		double[][] res = new double[20][2];
		for (int i = 0; i < res.length; i++) {
			res[i][0] = -10; 
			res[i][1] = 10;
		}
		return res;
	}
	
	
	@Override
	public double apply(double[] xs) {
		assert xs.length == 20;
		
		
		double sum = 0;
		
		for(int i = 0;i < 19; ++i) {
			sum += Math.pow(xs[i]-1, 2)*
				(1 + 10*Math.pow(Math.sin(Math.PI*xs[i+1]), 2));
		}
		
		sum += 10*Math.pow(Math.sin(Math.PI*xs[0]),2);
		sum += Math.pow(xs[19]-1, 2);
		
		return (Math.PI/20)*sum;
	}

	@Override
	public double[][] getDomain() {
		return domain;
	}
		

}
