package functions;

public class F1 extends Functions{
	
	private static final int dimension = 1;
	private static final double left_border =  0;
	private static final double right_border = 1;
	private static final double min = -1.1232;

	public double getResult(double[] k){
		double x = k[0];		
		double rez = 0;
		//double ep = 0.00000000001;
		//if(x<ep && y<ep){return 0;}
		rez = 2*(x-0.75)*(x-0.75)+Math.sin(5*Math.PI*x+0.4*Math.PI)-0.125;
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

