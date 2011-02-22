package antcolony;

public class AntAlgoritmParam{
	double alfa = 0.5;
	double beta = 0.5;
	int ant_count = 1;
	double sub_fer = 0.01;
	double q = 100; // otkladivaemij feromon;
	
	public AntAlgoritmParam(double al, double be, int an_c, double s_f, double q_f){
		this.alfa = al;
		this.beta = be;
		this.ant_count = an_c;
		this.sub_fer = s_f;	
		this.q = q_f;
	}
	
	public double getAlfa(){
		return alfa;
	}
	
	public double getBeta(){
		return beta;
	}
	
	public int getAntCount(){
		return ant_count;
	}
	
	public double getSubFer(){
		return sub_fer;
	}
	

}
