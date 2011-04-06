package pso.function;

import functions.*;
import java.util.LinkedList;
import pso.function.FunctionSolution;
import pso.Initializator;

public class FunctionInitializator extends Initializator<FunctionSolution, FunctionGeneration> {
	private double[] items;
	private int dimension = 0;
	private double leftBorder, rightBorder;
	private static java.util.Random rand = new java.util.Random();
	
	public static final int DEFAULT_GENERATION_SIZE = 5;
	private int generationSize;

	public FunctionInitializator(Functions f) {	
		this.dimension = f.getDimension();
		this.leftBorder = f.getLeftBorder();
		this.rightBorder = f.getRightBorder();
		this.generationSize = DEFAULT_GENERATION_SIZE;
		this.items = new double[this.dimension];
	}
	
	
	private FunctionSolution getSolution() {
		LinkedList<Double> itemSet = new LinkedList<Double>();
		for (int i = 0; i < this.dimension; ++i) {
			double border = rand.nextDouble() < 0.5 ? this.leftBorder : this.rightBorder;
			itemSet.add(border*rand.nextDouble());
		}
		return new FunctionSolution(itemSet);
	}
		
	public FunctionGeneration getInitialGeneration() {		
		FunctionGeneration result = new FunctionGeneration();
		
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
	
