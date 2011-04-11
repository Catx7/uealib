package functions;

public class Brown3 extends Functions{
		
	public Brown3(){
		dimension = 20;
		left_border =  -1;
		right_border = 4;
		min = 0;
	}
	
	public double getResult(double[] k){
		double rez = 0;	
		
		for(int i=0;i<19;i++){
			rez += (Math.pow(k[i]*k[i], k[i+1]*k[i+1]+1)+ Math.pow(k[i+1]*k[i+1], k[i]*k[i]+1));
		}		
		return rez;
	}	
	
}
