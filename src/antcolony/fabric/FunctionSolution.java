package antcolony.fabric;

import java.util.Vector;

public class FunctionSolution extends antcolony.fabric.AntSolutionFabric {	
	
	private double[] feromon;
	private Vector<Double> vect;
	private int memory = 5; //количество сообщений, которые помнит муравей
	private double[][] messages;
	private double[] best = new double[2];
	private int size;
	
	
	public FunctionSolution(double[] fer, Vector<Double> d){ //fer - количество феромона в точках
		this.feromon = fer;
		this.vect = d;	
		//System.out.println(vect);
		size = vect.size()/2;
		messages = new double[size][memory*3]; //каждое сообщение - 3 ячейки: координаты x, y и значение целевой функции
		for(int i=0;i<size;i++){
			for(int j=0;j<memory*3;j++){
				messages[i][j] = 10;
			}
		}
	}
	

	public double[] getFer(){		
		return this.feromon;
	}
	
	public Vector<Double> getVect(){		
		return this.vect;
	}
	
	public double getFitness() {
		double length = 0;
		for(int i=0;i<size;i++){
			if(getF(vect.get(i*2),vect.get(i*2+1))>length){
				length =getF(vect.get(i*2),vect.get(i*2+1));
				best[0] = vect.get(i*2);
				best[1] = vect.get(i*2+1);
			}
		}
		
		return length;
	}
	
	public double[] getBest(){
		getFitness();		
		
		return best;
	}
	
	public double[][] getMessages(){
		return this.messages;
	}
	
	public double getF(double x, double y){
		double rez = 0;
		double ep = 0.00000000001;
		if(x<ep && y<ep){return 0;}
		rez = Math.sin(x*x+y*y)/(x*x+y*y);		
		return rez;
	}

}
