package functions;

public class Fun1 extends Functions{
	
	private static final int dimension = 2;
	private static final double left_border = -1;
	private static final double right_border = 1;
	private static final double max = 1;
	
	public double getResult(double[] k){
		double x = k[0];
		double y = k[1];
		double rez = 0;
		//double ep = 0.00000000001;
		//if(x<ep && y<ep){return 0;}
		rez = Math.sin(x*x+y*y)/(x*x+y*y);	
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
		return max;
	}

	@Override
	public double getMin() {
		// TODO Auto-generated method stub
		return 0;
	}

}
