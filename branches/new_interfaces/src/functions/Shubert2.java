package functions;

public class Shubert2 extends Functions{
	
	private static final double b = 1;
	
	public Shubert2(){
		dimension = 2;
		left_border =  -10;
		right_border = 10;
		min = -186.7309;
	}
	
	
	public double getResult(double[] k){
		double x = k[0];
		double y = k[1];
		double rez = 0;
		//double ep = 0.00000000001;
		//if(x<ep && y<ep){return 0;}
		double sum1 = 0;
		double sum2 = 0;
		for(int i=1;i<6;i++){
			sum1+=i*Math.cos((i+1)*x+i);
			sum2+=i*Math.cos((i+1)*y+i);
		}
		rez = sum1*sum2;
		rez += (b*((x+1.42513)*(x+1.42513)+(y+0.80032)*(y+0.80032)));
		//System.out.println(rez);
		return rez;
	}
	


}

