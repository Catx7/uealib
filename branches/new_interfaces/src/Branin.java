package functions;

public class Branin extends Functions{
	
	private static final int dimension = 2;
	private static final double left_border =  0;
	private static final double right_border = 30;
	private static final double min = 0.3979;
	private static final double a = 1;
	private static final double b = 5.1/(4*Math.PI*Math.PI);
	private static final double c = 5/Math.PI;
	private static final double d = 6;
	private static final double h = 10;
	private static final double g = 1/(8*Math.PI);
	
	public double getResult(double[] k){
		double x = k[0];
		double y = k[1];
		double rez = 0;
		//double ep = 0.00000000001;
		//if(x<ep && y<ep){return 0;}
		rez = a*(y-b*x*x+c*x-d)*(y-b*x*x+c*x-d)+h*(1-g)*Math.cos(x)+h;
		return rez;
	}
	
	public int getDimension(){
		return dimension;
	}
	
	public double getLeftBorder(){
		return left_border;		
	}
	
	public double getRightBorder(){
		return right_border;		
	}
	
	public double getMax(){
		// TODO Auto-generated method stub
		return 0;
	}


	public double getMin() {
		return min;
	}

}

