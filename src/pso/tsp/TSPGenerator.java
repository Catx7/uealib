package pso.tsp;

import pso.tsp.TSPGeneration;
import pso.tsp.TSPSolution;

public class TSPGenerator implements pso.Generator<TSPGeneration , TSPSolution>{

	private double a = 0.7298,	b = 1.49618, c = 1.49618;
	private double [] Ub , Uc;
	
	
	public void setParam(double a , double b , double c) {
		this.a = a;
		this.b = b;
		this.c = c;		
	}

	
	public TSPGeneration getNextGeneration(TSPGeneration currentGeneration, TSPGeneration bestGeneration, TSPSolution bestSolution) {
		
		int n = bestSolution.length();
		Ub = new double [n];
		Uc = new double [n];
		int [] route = new int [n];
		int [] speed = new int [n];
		for (int i=0; i < bestSolution.length(); i++) {
			Ub[i]=1/b;
			Uc[i]=1/c;
			speed[i]=0;
		}
		
		TSPGeneration g = new TSPGeneration();
		int Gn = currentGeneration.size(); 
			
		for(int k = 0; k < Gn; ++k) {	
			for (int i = 0; i < n; ++i) {
				speed[i] = (int)(a*speed[i] + Ub[i]*(bestGeneration.get(k).get(i) - currentGeneration.get(k).get(i)) + Uc[i]*(bestSolution.get(i) - currentGeneration.get(k).get(i)));
				route[i] += speed[i];
				if(route[i] < 0)
					route[i] = 0;
				if (route[i] > n-1)
					route[i] = n-1;
		    }
			TSPSolution nextRoute = new TSPSolution(route).decode();
		    g.add(nextRoute);
		}
	    return g;	
	}
	
}
