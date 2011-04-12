package usablefunctions;

public class F5n implements Function {
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
		
		double ys[] = new double[xs.length];
		for (int i = 0; i < ys.length; i++) {
			ys[i] = 1 + 0.25*(xs[i]-1);
		}		
		
		
		double sum = 0;
		
		for(int i = 0;i < 19; ++i) {
			sum += Math.pow(ys[i]-1, 2)*
				(1 + 10*Math.pow(Math.sin(Math.PI*ys[i+1]), 2));
		}
		
		sum += 10*Math.pow(Math.sin(Math.PI*ys[0]),2);
		sum += Math.pow(ys[19]-1, 2);
		
		return (Math.PI/20)*sum;
	}

	@Override
	public double[][] getDomain() {
		return domain;
	}
		

}
