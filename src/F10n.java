package functions;

public class F10n extends Functions{
	
	private static final int dimension = 20;
	private static final double left_border =  -10;
	private static final double right_border = 10;
	private static final double min = 0;	
	
	public double getResult(double[] k){
		double rez = 0;
		for(int i=1;i<19;i++){
			rez += (k[i]-1)*(k[i]-1)*(1+10*Math.sin(Math.PI*k[i+1])*Math.sin(Math.PI*k[i+1]));			
		}
		rez += 10*Math.sin(Math.PI*k[0])*Math.sin(Math.PI*k[0]);
		rez += (k[19]-1)*(k[19]-1);
		rez *= (Math.PI/20);
		
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

