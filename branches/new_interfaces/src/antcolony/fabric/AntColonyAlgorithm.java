package antcolony.fabric;

import java.util.Vector;


import javax.swing.JFrame;

import antcolony.illustration.Draw;
import readers.Graph;
import antcolony.fabric.*;
import functions.*;

public class AntColonyAlgorithm {
	
	private double[][] m;
	private GenerationFabric Gen;
	private InitializatorFabric Ini;
	private AntSolutionFabric A_s;
	private GenerationFabric Gen1;
	private GenerationFabric Gen0;
	private GeneratorFabric Gen_or;
	private AntAlgoritmParam Aap;
	private Functions funct;
		
	public AntColonyAlgorithm(Graph gr){
	
	////////////////////////////////////////////////
	///	***commisvoyageor task***
	///	m = gr.getWeights();
	///	Ini = new CommisvoyageorInitializator(m);
	///	Gen = Ini.getInitialGeneration();
	///	Aap = new AntAlgoritmParam(0.9,0.1,1,5,100);
	///	Gen_or = new CommisvoyageorGenerator(m,0,Aap);
		
		//m = gr.getWeights();
		
	////////////////////////////////////////////////
	///	***backpack task***
		/*double[] mas = new double[10];
		mas[0] = 1;
		mas[1] = 3;
		mas[2] = 2;
		mas[3] = 10;
		mas[4] = 6;
		mas[5] = 32;
		mas[6] = 5;
		mas[7] = 2;
		mas[8] = 9;
		mas[9] = 1;
		m = new double[10][10];
		for(int i=0;i<10;i++){
			m[i][0]=0.5;
			m[i][1]=4;
			m[i][2]=3;
			m[i][3]=7;
			m[i][4]=4;
			m[i][5]=31;
			m[i][6]=3.1;
			m[i][7]=4.6;
			m[i][8]=12;
			m[i][9]=1;
		}
		Ini = new BackpackInitializator(m, 32,mas);
		Gen = Ini.getInitialGeneration();
		Aap = new AntAlgoritmParam(0.5,0.5,1,0,100);
		Gen_or = new BackpackGenerator(m,0,Aap);*/
		
		
		
		
	//////////////////////////////////////////////////////////////////	
	///	***function task***
		Ini = new FunctionInitializator(3, funct);
		Gen = Ini.getInitialGeneration();
		FunctionSolutionMin s = (FunctionSolutionMin)Gen.getSolution();
	///	//System.out.println(s.getVect());
	///	//System.out.println(s.getBest()[0]);
	///	//System.out.println(s.getBest()[1]);
		//System.out.println(s.getFitness());
//	///	Aap = new AntAlgoritmParam(0.5,0.5,1,0,10);
		Gen_or = new FunctionGenerator(s.getVect());
		/*FunctionSolution fss = (FunctionSolution)Gen.getSolution();
		Vector<Double> st = fss.getVect();
		Draw dd = new Draw(st);
		dd.drawField(((FunctionSolution)Gen.getSolution()).getVect());*/
		
	}
	
	public AntColonyAlgorithm(Functions f){
		funct = f;
		Ini = new FunctionInitializator(1000, funct);
		Gen = Ini.getInitialGeneration();
		FunctionSolutionMin s = (FunctionSolutionMin)Gen.getSolution();
		Gen_or = new FunctionGeneratorMin(s.getVect());
	}

	
	public void solve(){
		StoppingCriteria stop = new StoppingCriteria();
		TransitionCriteria trans = new TransitionCriteria();
		Context con = new Context();
		con.setCount(10000);
		
		//double fit = Gen.getSolution().getFitness();
		int[] path = Gen.getSolution().GetResult();
		double fg=0;
		//int[] rezz = new int[path.length]; 
		
		
/////////////////////////////////////////////////////////////////
	/// ***function task***
		while(!stop.isSatisfied(Gen)){
		//	double f = Gen.getSolution().getFitness();
			Gen1 =Gen_or.getNext(Gen);		
		//	double f1 = Gen1.getSolution().getFitness();
			//if(f>f1){Gen = Gen1;fg = f1;rezz = Gen.getSolution().GetResult();}
			//if(f<f1){Gen = Gen1;
			//fg = f1;rezz = Gen.getSolution().GetResult();
			//}
		/*	double f1 = Gen1.getSolution().getFitness();
			double x = Gen1.getSolution().getBest()[0];
			double y = Gen1.getSolution().getBest()[1];
			System.out.println(f1);
			System.out.println(x);
			System.out.println(y);*/
		
		}
		
		
	/////////////////////////////////////////////////////////////////
	/// ***function task***
		FunctionSolutionMin fs = (FunctionSolutionMin)Gen1.getSolution();
		double[][] tt = fs.getVect();
		//Draw t = new Draw(tt);
	    // t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //
		//t.setVisible(true);
		/*Draw d = new Draw(t);
		d.drawField(((FunctionSolution)Gen1.getSolution()).getVect());*/
		//double f1 = ((FunctionSolution)Gen1.getSolution()).getFitness();
	      double f1 = fs.getFitness();
		double x = Gen1.getSolution().getBest()[0];
		double y = Gen1.getSolution().getBest()[1];
		System.out.println(f1);
		System.out.println(x);
		System.out.println(y);
			
			
	///	////////////////////////////////////////
	/// ***commisvoyageor task***
		//while(!stop.isSatisfied(Gen)){
	/*	for(int i=0;i<5000;i++){
			double f = Gen.getSolution().getFitness();
			Gen1 =Gen_or.getNext(Gen);		
			double f1 = Gen1.getSolution().getFitness();
			//if(f>f1){Gen = Gen1;fg = f1;rezz = Gen.getSolution().GetResult();}
			if(f>f1){
				//Gen = Gen1;
				fg = f1;rezz = Gen.getSolution().GetResult();}
			
		}
		
		A_s = Gen.getSolution();
		fit = A_s.getFitness();
		path = A_s.GetResult();
		//System.out.println(A_s.getFitness());
		//System.out.println(fg);
		String pathln = "";
		for(int i=0;i<path.length;i++){
			pathln += rezz[i];
			pathln += " ";
		}
		//System.out.println(pathln);
		 */
		
		
				
	}
	
}


