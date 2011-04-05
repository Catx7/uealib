package diffevolution.functions;

import java.util.LinkedList;

import diffevolution.Initializator;
import functions.Functions;


public class FInitializator<F extends Functions> extends Initializator<FSolution, FGeneration> {

	public static final int DEFAULT_GENERATION_SIZE = 100;
	private static java.util.Random rand = new java.util.Random();
	private int generationSize;
	private F function;
	private int n;

	public FInitializator(F function) {	
		this.generationSize = DEFAULT_GENERATION_SIZE;
		this.function = function;
		this.n = function.getDimension();
	}
	
	
	private FSolution getSolution() {
		LinkedList<Double> solution = new LinkedList<Double>();
		double right = this.function.getRightBorder();
		double left = this.function.getLeftBorder();
		double interval = right - left;
		//System.out.println(interval);
		for ( int i = 0; i < this.n; ++i ) 
			solution.add(left + Math.random()*interval);

		return new FSolution(solution);
	}
	
	
	public FGeneration getInitialGeneration() {		
		FGeneration result = new FGeneration();
		FGeneration.left = this.function.getLeftBorder();
		FGeneration.right = this.function.getRightBorder();
		int inx = 0;
		while ( inx < this.generationSize ) {
	  		result.add(getSolution());
	    	++inx;
		}
		return result;
	}

	public void setGenerationSize(int size) {
		this.generationSize = size;		
	}
	
}
	
