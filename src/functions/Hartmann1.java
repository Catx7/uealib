package functions;

public class Hartmann1 extends Functions{
	
	public static final double[] c = {1, 1.2, 3, 3.2};
	public static final double[][] a = {{3,10,30},{0.1,10,35},{3,10,30},{0.1,10,35}};
	public static final double[][] p = {{0.3689,0.117,0.2673},{0.4699,0.4387,0,747},{0.1091,0.8732,0.5547},{0.0382,0.5743,0.8828}};

	public Hartmann1(){
		dimension = 3;
		left_border =  0;
		right_border = 1;
		min =  -3.8628;
	}
	
	public double getResult(double[] k){
		double rez = 0;
		for(int i=0;i<4;i++){
			double sum = 0;
			for(int j=0; j<3; j++){
				sum += a[i][j]*(k[j]-p[i][j])*(k[j]-p[i][j]);
			}
			sum *= -1;
			rez += (c[i]*Math.exp(sum));
		}
		
		rez *= (-1);
		return rez;
	}	
	
}

