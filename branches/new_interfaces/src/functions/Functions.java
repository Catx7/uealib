package functions;

public abstract class Functions {
	
	public abstract double getResult(double[] k);
	
	public int dimension;
	public double left_border;
	public double right_border;
	public double min ;
	public double max;
	
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


	public double getMin() {
		return min;
	}

	


}
