package functions;

public class F3 extends Functions{
	
	public F3(){
		dimension = 1;
		left_border =  -10;
		right_border = 10;
		min =  -12.0312;
	}

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
	
}

