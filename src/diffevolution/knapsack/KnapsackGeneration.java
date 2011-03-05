package diffevolution.knapsack;


public class KnapsackGeneration  extends diffevolution.Generation<KnapsackSolution> {

	private static final long serialVersionUID = 1L;
	
		@Override
		public KnapsackGeneration getBest() {
			return this;
		}
			
		public KnapsackSolution mutate(KnapsackSolution chromosome1, KnapsackSolution chromosome2, KnapsackSolution chromosome3, int F){
			int n = chromosome1.length();
			int[] result = new int[n];
		    for(int i = 0; i < n; ++i)	{
		    	int lim = KnapsackSolution.maxItemNum[i];
		        result[i] = (int) Math.abs((chromosome1.get(i) + 2*(chromosome1.get(i) - chromosome1.get(i)) % lim) % lim);
		    }
		    return new KnapsackSolution(result);
		}
		
		public String getRepresentation() {
			String representation = "Generation[";
			for (KnapsackSolution solution: this)
				representation += "\n  " + solution.getRepresentation();
			return representation + "\n]";
		}

		

}
