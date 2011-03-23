package antcolony.fabric;

import java.util.Random;
import java.util.Vector;

import antcolony.illustration.Draw;

public class FunctionInitializator extends InitializatorFabric{
	
	private Vector<Double> d;
	Random rn = new Random();
	private double[] fer;
	
	public FunctionInitializator(int col) { //col - количество муравьев
		d = new Vector<Double>();
		fer = new double[col];
		
		for(int i=0;i<col;i++){
			fer[i]=1;
			double x = rn.nextDouble()*2-1;//что бы лежал в промежутке от -1 до 1
			double y = rn.nextDouble()*2-1;
		//	double x = rn.nextDouble();//что бы лежал в промежутке от -1 до 1
		//	double y = rn.nextDouble();
			
			d.add(x);
			d.add(y);
		}
		
		Draw dd = new Draw(d);
		dd.drawField(d);
				
	}

	public GenerationFabric getInitialGeneration() {
		int[] rez  = new int[n];
		AntAlgoritmParam ant_al = new AntAlgoritmParam(0.5,0.5,1,0,100);
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		antcolony.fabric.GeneratorFabric gen = new antcolony.fabric.FunctionGenerator(d);
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				
		AntSolutionFabric s = new FunctionSolution(fer,d);
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		g_i = new FunctionGeneration(s);
	
		FunctionGeneration g_i1 = new FunctionGeneration(gen.getNext(g_i).getSolution());
		//System.out.println(g_i1.getSolution().getBest()[0]);

		return g_i1;
	}

}
