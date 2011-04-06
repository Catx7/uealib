package pso.function;

import readers.items.Item;

import functions.*;

import pso.Evaluator;


public class FunctionEvaluator extends Evaluator<FunctionSolution> {

	private Item[] items;
	private int dimension = 0;
	private double leftBorder, rightBorder;
	private int n;
	private Functions func;
	
	public FunctionEvaluator(Functions f) {
		this.dimension = f.getDimension();
		this.leftBorder = f.getLeftBorder();
		this.rightBorder = f.getRightBorder();
		this.func = f;
	}
	
	public double evaluate(FunctionSolution solution) {
		assert n == solution.length();
		
		double value = this.func.getResult(solution.toArray());		
		return value;
	}
	
	public double getFitness(FunctionSolution solution) {
		return -evaluate(solution);
	}
}