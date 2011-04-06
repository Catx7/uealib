package pso.function;

import pso.function.FunctionGeneration;
import pso.function.FunctionSolution;


public class FunctionGenerator implements pso.Generator<FunctionGeneration , FunctionSolution>{

	private double a = 0.7298,	b = 1.49618, c = 1.49618;
	private double [] Ub , Uc;
	
	
	public void setParam(double a , double b , double c) {
		this.a = a;
		this.b = b;
		this.c = c;		
	}

	
	public FunctionGeneration getNextGeneration(FunctionGeneration currentGeneration, FunctionGeneration bestGeneration, FunctionSolution bestSolution) {
		
		int n = bestSolution.length();
		Ub = new double [n];
		Uc = new double [n];
		double [] route = new double [n];
		double [] speed = new double [n];
		for (int i=0; i < bestSolution.length(); i++) {
			Ub[i]=i/(b*n);
			Uc[i]=i/(c*n);
			speed[i]=0;
		}
		
		FunctionGeneration g = new FunctionGeneration();
		int Gn = currentGeneration.size(); 
			
		for(int k = 0; k < Gn; ++k) {	
			for (int i = 0; i < n; ++i) {
				speed[i] = (a*speed[i] + Ub[i]*(bestGeneration.get(k).get(i) - currentGeneration.get(k).get(i)) + Uc[i]*(bestSolution.get(i) - currentGeneration.get(k).get(i)));
				route[i] += speed[i];
		    }
			FunctionSolution nextRoute = new FunctionSolution(route);
		    g.add(nextRoute);
		}
	    return g;	
	}
}
