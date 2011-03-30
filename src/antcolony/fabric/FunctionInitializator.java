package antcolony.fabric;

import java.util.Random;
import java.util.Vector;

import antcolony.illustration.Draw;
import functions.*;

public class FunctionInitializator extends InitializatorFabric{
	
	Random rn = new Random();
	private double[] fer;
	private Functions funct;
	private double[][] d_koord;
	private int dim;
	
	public FunctionInitializator(int col, Functions f) { //col - количество муравьев
		dim = f.getDimension();
		d_koord = new double[col][dim];		
		fer = new double[col];		
		funct = f;
		double l_b = f.getLeftBorder();
		double r_b = f.getRightBorder();
		double mn = (r_b - l_b)/2;
		double t_o = l_b + mn;
		
		for(int i=0;i<col;i++){
			fer[i]=1;
		//	double x = rn.nextDouble()*2-1;//что бы лежал в промежутке от -1 до 1
		//	double y = rn.nextDouble()*2-1;
			for(int j=0;j<dim;j++){
				double fl0 = -1;				
				if(rn.nextBoolean() == true){fl0 = 1;}
				double x = t_o + fl0*mn*rn.nextDouble();
				d_koord[i][j] = x;						
			}
		/*	double fll0 = -1;
			double fll2 = -1;
			if(rn.nextBoolean() == true){fll0 = 1;}
			if(rn.nextBoolean() == true){fll2 = 1;}
			double x = t_o + fll0*mn*rn.nextDouble();
			double y = t_o + fll2*mn*rn.nextDouble();		
			
			d.add(x);
			d.add(y);*/
		}
		
		//Draw dd = new Draw(d_koord);
		//dd.drawField(d_koord);
		
		
				
	}

	public GenerationFabric getInitialGeneration() {
		int[] rez  = new int[n];
		AntAlgoritmParam ant_al = new AntAlgoritmParam(0.5,0.5,1,0,100);
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		antcolony.fabric.GeneratorFabric gen = new antcolony.fabric.FunctionGeneratorMin(d_koord);
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				
		AntSolutionFabric s = new FunctionSolutionMin(fer,d_koord, funct);
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		g_i = new FunctionGeneration(s);
	
		FunctionGeneration g_i1 = new FunctionGeneration(gen.getNext(g_i).getSolution());
		//System.out.println(g_i1.getSolution().getBest()[0]);

		return g_i1;
	}

}
