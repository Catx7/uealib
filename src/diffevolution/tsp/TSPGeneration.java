package diffevolution.tsp;


public class TSPGeneration extends diffevolution.Generation<TSPSolution> {

		private static final long serialVersionUID = 1L;

		@Override
		public TSPGeneration getBest() {
			TSPGeneration best = new TSPGeneration();
			best.add(this.get(0));
			return best;
		}
		
		public TSPSolution mutate(TSPSolution chromosome1, TSPSolution chromosome2, TSPSolution chromosome3, int F){
			int n = chromosome1.length();
			int[] result = new int[n];
		    for(int i = 0; i < n; ++i)	// F
		        result[i] = (int) Math.abs((chromosome1.get(i) + F*(chromosome1.get(i) - chromosome1.get(i)) % (n - i)) % (n - i));
	        return new TSPSolution(result);
		}
		
		public String getRepresentation() {
			String representation = "Generation[";
			for (TSPSolution solution: this)
				representation += "\n  " + solution.getRepresentation(); // Q&D
			return representation + "\n]";
		}
	}

