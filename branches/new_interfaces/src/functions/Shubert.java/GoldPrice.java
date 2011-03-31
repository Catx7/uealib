package functions;

public class GoldPrice extends Functions{

	public GoldPrice(){
		dimension = 2;
		left_border =  -1;
		right_border = 1;
		min =  3;
	}
	
	public double getResult(double[] k){
		double x = k[0];
		double y = k[1];
		double rez = 0;
		//double ep = 0.00000000001;
		//if(x<ep && y<ep){return 0;}
		rez = (1+(x+y+1)*(x+y+1)*(19-14*x+3*x*x-14*y+6*x*y+3*y*y))*(30+(2*x-3*y)*(2*x-3*y)*(18-32*x+12*x*x+48*y-36*x*y+27*y*y));
		return rez;
	}	
	
}

