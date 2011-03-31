package functions;

public class F1 extends Functions{
	
	
	public F1(){
		dimension = 1;
		left_border =  0;
		right_border = 1;
		min = -1.1232;
	}

	public double getResult(double[] k){
		double x = k[0];		
		double rez = 0;
		//double ep = 0.00000000001;
		//if(x<ep && y<ep){return 0;}
		rez = 2*(x-0.75)*(x-0.75)+Math.sin(5*Math.PI*x+0.4*Math.PI)-0.125;
		return rez;
	}	
	

}

