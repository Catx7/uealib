package antcolony;

import readers.Graph;

public class AntColonyAlgorithm {
	
	private double[][] m;
	private antcolony.Generation Gen;
	private Initializator Ini;
	private AntSolution A_s;
	private antcolony.Generation Gen1;
	private antcolony.Generation Gen0;
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
		TransitionCriteria trans = new TransitionCriteria();
		Context con = new antcolony.Context();
		con.setCount(1000);
		
		double fit = Gen.getSolution().getFitness();
		int[] path = Gen.getSolution().GetResult();
		double fg=0;
		int[] rezz = new int[path.length]; 
		
		while(!stop.isSatisfied(Gen)){
			double f = Gen.getSolution().getFitness();
			Gen1 =Gen_or.getNext(Gen);		
			double f1 = Gen1.getSolution().getFitness();
			if(f>f1){Gen = Gen1;fg = f1;rezz = Gen.getSolution().GetResult();}
			
		}
		
	//	A_s = Gen.getSolution();
	//	fit = A_s.getFitness();
	//	path = A_s.GetResult();
		//System.out.println(A_s.getFitness());
		System.out.println(fg);
		String pathln = "";
		for(int i=0;i<path.length;i++){
			pathln += rezz[i];
			pathln += " ";
		}
		System.out.println(pathln);
		
		
				
	}
	
}
