package antcolony.illustration;

public class Function {
	
	public double getFunction(double x, double y){
		double rez = 0;
		double ep = 0.00000001;
		if(x<ep && y<ep){return 0;}
		rez = Math.sin(x*x+y*y)/(x*x+y*y);		
		return rez;
	}

}
