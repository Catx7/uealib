package functions;

public class F3 extends Functions{
	
	private static final int dimension = 1;
	private static final double left_border =  -10;
	private static final double right_border = 10;
	private static final double min = -12.0312;

	public double getResult(double[] k){
		double x = k[0];		
		double rez = 0;
		//double ep = 0.00000000001;
		//if(x<ep && y<ep){return 0;}
		for(int i=1;i<6;i++){
			rez += i*Math.sin((i+1)*x+i);
		}
		rez *= (-1);
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

