package functions;

public class F10n extends Functions{

	public F10n(){
		dimension = 20;
		left_border =  -10;
		right_border = 10;
		min =  0;
	}
	
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

}

