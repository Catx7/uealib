package functions;

public class F5n extends Functions{

	public F5n(){
		dimension = 20;
		left_border =  -10;
		right_border = 10;
		min =  0;
	}
	
	public double getResult(double[] k){
		double rez = 0;
		double y = 0;
		for(int i=1;i<19;i++){
			y = 1+0.25*(k[i]-1);
			rez += (y-1)*(y-1)*(1+10*Math.sin(Math.PI*y)*Math.sin(Math.PI*y));			
		}
		y = 1+0.25*(k[0]-1);
		rez += 10*Math.sin(Math.PI*y)*Math.sin(Math.PI*y);
		y = 1+0.25*(k[19]-1);	
		rez += (y-1)*(y-1);
		rez *= (Math.PI/20);
		
		return rez;
	}	

}

