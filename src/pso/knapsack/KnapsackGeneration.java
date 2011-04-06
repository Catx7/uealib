package pso.knapsack;


public class KnapsackGeneration  extends pso.Generation<KnapsackSolution> {

	private static final long serialVersionUID = 1L;
	
		@Override
		public KnapsackGeneration getBest() {
			return null;
		}
			
		public String getRepresentation() {
			String representation = "Generation[";
			for (KnapsackSolution solution: this)
				representation += "\n  " + solution.getRepresentation();
			return representation + "\n]";
		}

		

}
