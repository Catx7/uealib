package functions;

public class Branin extends Functions{
	
	private static final double a = 1;
	private static final double b = 5.1/(4*Math.PI*Math.PI);
	private static final double c = 5/Math.PI;
	private static final double d = 6;
	private static final double h = 10;
	private static final double g = 1/(8*Math.PI);
	
	public Branin(){
		dimension = 2;
		left_border =  0;
		right_border = 30;
		min =  0.3979;
	}
	
	public double getResult(double[] k){
		double x = k[0];
		double y = k[1];
		double rez = 0;
		//double ep = 0.00000000001;
		//if(x<ep && y<ep){return 0;}
		rez = a*(y-b*x*x+c*x-d)*(y-b*x*x+c*x-d)+h*(1-g)*Math.cos(x)+h;
		return rez;
	}	


}

