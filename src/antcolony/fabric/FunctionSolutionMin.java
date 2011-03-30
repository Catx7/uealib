package antcolony.fabric;

import java.util.Vector;
import functions.*;

public class FunctionSolutionMin extends antcolony.fabric.AntSolutionFabric {	
	
	private double[] feromon;
	private double[][] vect;
	private int memory = 5; //количество сообщений, которые помнит муравей
	private double[][][] messages;
	private double[] best = new double[2];
	private int size;
	private Functions funct;
	private int dim;
		
	public FunctionSolutionMin(double[] fer, double[][] d, Functions f){ //fer - количество феромона в точках
		this.feromon = fer;
		this.vect = d;	
		dim = f.getDimension();
		//System.out.println(vect);
		//size = vect.length/vect[0].length;
		size = vect.length;
		//System.out.println(size);
		//vect = new double[size][dim];
		messages = new double[size][memory][dim+1]; //каждое сообщение - 3 ячейки: координаты x, y и значение целевой функции
		for(int i=0;i<size;i++){
			for(int j=0;j<memory;j++){
				for(int k=0;k<dim+1;k++){
					messages[i][j][k] = 10;
				}
			}
		}
		funct = f;
	}
	

	public double[] getFer(){		
		return this.feromon;
	}
	
	public double[][] getVect(){		
		return this.vect;
	}
	
	public double getFitness() {
		double length = -1000000000;
		for(int i=0;i<size;i++){
			
			if(funct.getResult(vect[i])>length){
				//length =getF(vect.get(i*2),vect.get(i*2+1));
				length = funct.getResult(vect[i]);
				best[0] = vect[i][0];
			//	best[1] = vect[i][1];
			}
		}
		//System.out.println(best[0]+" "+best[1]);
		return length;
	}
	
	public double[] getBest(){
		getFitness();		
		return best;
	}
	
	public double[][][] getMessages(){
		return this.messages;
	}
	
	public double getF(double x, double y){
		double rez = 0;
		double ep = 0.00000000001;
		if(x<ep && y<ep){return 0;}
		rez = Math.sin(x*x+y*y)/(x*x+y*y);		
		return rez;
	}
	
	public Functions getFunction(){
		return funct;
	}

}
