package functions;

public class Brown1 extends Functions{
		
	public Brown1(){
		dimension = 20;
		left_border =  -1;
		right_border = 4;
		min = 2;
	}
	
	public double getResult(double[] k){
		double rez = 0;
		double sum1 = 0;
		double sum2 = 0;
		
		for(int i=0;i<19;i++){
			sum1 += (k[i]-3);
			sum2 += (0.001*(k[3]-3)*(k[i]-3)-(k[i]-k[i+1])+ Math.pow(Math.E, 20*(k[i]-k[i+1])));
		}
		sum1 *= sum1;
		rez = sum1 + sum2;
		
		return rez;
	}	
	
}
