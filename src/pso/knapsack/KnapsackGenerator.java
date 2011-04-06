package pso.knapsack;


public class KnapsackGenerator implements pso.Generator<KnapsackGeneration , KnapsackSolution>{

	private double a = 0.7298,	b = 1.49618, c = 1.49618;
	private double [] Ub , Uc;
	
	
	public void setParam(double a , double b , double c) {
		this.a = a;
		this.b = b;
		this.c = c;		
	}

	
	public KnapsackGeneration getNextGeneration(KnapsackGeneration currentGeneration, KnapsackGeneration bestGeneration, KnapsackSolution bestSolution) {
		
		int n = bestSolution.length();
		Ub = new double [n];
		Uc = new double [n];
		int [] route = new int [n];
		double [] speed = new double [n];
		for (int i=0; i < bestSolution.length(); i++) {
			Ub[i]=1/b;
			Uc[i]=1/c;
			speed[i]=0;
		}
		
		KnapsackGeneration g = new KnapsackGeneration();
		int Gn = currentGeneration.size(); 
			
		for(int k = 0; k < Gn; ++k) {	
			for (int i = 0; i < n; ++i) {
				speed[i] = (a*speed[i] + Ub[i]*(bestGeneration.get(k).get(i) - currentGeneration.get(k).get(i)) + Uc[i]*(bestSolution.get(i) - currentGeneration.get(k).get(i)));
				//speed[i] = Math.abs(speed[i]-(int)speed[i]) < 0.5 ? (int)speed[i] : (int)speed[i] + 1;
				
				route[i] += (int)speed[i];
				if(route[i] > 1) 
					route[i] = 1;
				if(route[i] < 0) 
					route[i] = 0;
		    }
			KnapsackSolution nextRoute = new KnapsackSolution(route);
		    g.add(nextRoute);
		}
	    return g;	
	}
}
