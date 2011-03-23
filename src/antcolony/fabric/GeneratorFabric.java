package antcolony.fabric;

import core.Generation;
import core.Solution;
import java.util.Random;


public abstract class GeneratorFabric implements core.Generator{
	
	protected double[][] weight;
	protected double[][] feromon;
	protected double[][] feromon1;
	protected int n = 0;
	protected int[] result;
	protected int[] result1;
	protected int v0 = 0;
	protected int v = 0;
	protected int v_tmp = 0;
	protected AntAlgoritmParam a;
	protected int[][] tut_bili;
	protected int[] path ;
	protected int[] zahodili; 
	protected int tmp = 0;
	protected AntSolutionFabric s;
	Random rn = new Random();
	
	
	
	public abstract antcolony.fabric.GenerationFabric getNext(Generation g);
	
	
	
	public AntSolutionFabric getNextSolution(AntSolutionFabric s){
		feromon = s.GetFeromon();
		AntSolutionFabric ss;
		if(s instanceof CommisvoyageorSolution){
			ss = new CommisvoyageorSolution(result, feromon, weight);
		 }
		else{
			ss = new BackpackSolution(result, feromon, weight);			
		}
		return ss;
	}
	
	

	
}
