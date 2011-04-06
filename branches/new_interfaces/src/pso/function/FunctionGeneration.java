package pso.function;

import pso.function.FunctionSolution;

public class FunctionGeneration  extends pso.Generation<FunctionSolution> {

	private static final long serialVersionUID = 1L;
	
		@Override
		public FunctionGeneration getBest() {
			return this;
		}
	
		public String getRepresentation() {
			String representation = "Generation[";
			for (FunctionSolution solution: this)
				representation += "\n  " + solution.getRepresentation();
			return representation + "\n]";
		}

		

}
