package functions;

public class Fun1 extends Functions{
		
	public Fun1(){
		dimension = 2;
		left_border =  -1;
		right_border = 1;
		max = 1;
	}
	
	public double getResult(double[] k){
		double x = k[0];
		double y = k[1];
		double rez = 0;
		//double ep = 0.00000000001;
		//if(x<ep && y<ep){return 0;}
		rez = Math.sin(x*x+y*y)/(x*x+y*y);	
		return rez;
	}	
	
}
