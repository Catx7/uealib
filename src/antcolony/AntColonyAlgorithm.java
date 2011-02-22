package antcolony;

import readers.Graph;

public class AntColonyAlgorithm {
	
	private double[][] m;
	private antcolony.Generation Gen;
	private Initializator Ini;
	private AntSolution A_s;
	private Generation Gen1;
	private Generator Gen_or;
	private AntAlgoritmParam Aap;
		
	public AntColonyAlgorithm(Graph gr){
			
		m = gr.getWeights();
		Ini = new Initializator(m);
		Gen = Ini.getInitialGeneration();
		Aap = new AntAlgoritmParam(0.5,0.5,1,0,100);
		Gen_or = new Generator(m,0,Aap);
		//Gen1 = Gen_or.getNext(Gen);
		
	}

	
	public void solve(){
		StoppingCriteria stop = new StoppingCriteria();
		Context con = new antcolony.Context();
		con.setCount(100);
		
		double fit = Gen.getSolution().getFitness();
		int[] path = Gen.getSolution().GetResult();
		
		while(!stop.isSatisfied(Gen)){
			Gen = Gen_or.getNext(Gen);
			A_s = Gen.getSolution();
			//System.out.println(A_s.getFitness());
			if(A_s.getFitness() < fit){
				fit = A_s.getFitness();
				path = A_s.GetResult();
			}
		}
		
		//A_s = Gen.getSolution();
		//System.out.println(A_s.getFitness());
		System.out.println(fit);
		String pathln = "";
		for(int i=0;i<path.length;i++){
			pathln += path[i];
			pathln += " ";
		}
		System.out.println(pathln);
		
		
				
	}
	
}
