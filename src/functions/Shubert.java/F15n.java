package functions;

public class F15n extends Functions{

	public F15n(){
		dimension = 20;
		left_border =  -10;
		right_border = 10;
		min =  0;
	}
	
	public double getResult(double[] k){
		double rez = 0;

		for(int i=1;i<19;i++){
			rez += ((k[i]-1)*(k[i]-1)*(1+Math.sin(3*Math.PI*k[i+1])*Math.sin(3*Math.PI*k[i+1])));			
		}
		rez += (Math.sin(3*Math.PI*k[0])*Math.sin(3*Math.PI*k[0])+(1/10)*(k[19]-1)*(k[19]-1)*(1+Math.sin(2*Math.PI*k[19])*Math.sin(2*Math.PI*k[19])));
		rez /= 10;
		
		return rez;
	}	

}

