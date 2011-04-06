package pso.tsp;

import pso.tsp.TSPGeneration;
import pso.tsp.TSPSolution;

public class TSPGeneration extends pso.Generation<TSPSolution> {

		private static final long serialVersionUID = 1L;
		
		//@Override
		public TSPGeneration getBest() {
			return this;
		}
		
		public String getRepresentation() {
			String representation = "Generation[";
			for (TSPSolution solution: this)
				representation += "\n  " + solution.getRepresentation(); // Q&D
			return representation + "\n]";
		}
}

