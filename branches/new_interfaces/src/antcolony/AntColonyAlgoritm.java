package antcolony;

public class AntColonyAlgoritm {
	double alfa = 0.5;
	double beta = 0.5;
	int ant_count = 1;
	double sub_fer = 0.01;
	
	public AntColonyAlgoritm(double al, double be, int an_c, double s_f){
		alfa = al;
		beta = be;
		ant_count = an_c;
		sub_fer = s_f;		
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
