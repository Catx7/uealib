package diffevolution.functions;


public class FGeneration extends diffevolution.Generation<FSolution> {

			private static final long serialVersionUID = 1L;
			public static double right;
			public static double left;
			
			@Override
			public FGeneration getBest() {
				return this;
			}
			
			public FSolution mutate(FSolution chromosome1, FSolution chromosome2, FSolution chromosome3, int F){
				int n = chromosome1.length();
				double[] result = new double[n];
				double interval = FGeneration.right - FGeneration.left;
				
			    for(int i = 0; i < n; ++i)	{// F
			        result[i] = F*(chromosome1.get(i) - chromosome1.get(i));
			        while (result[i] > FGeneration.right ) result[i] -= interval; 
			        while (result[i] < FGeneration.left ) result[i] += interval;
			        result[i] += chromosome1.get(i);
			        while (result[i] > FGeneration.right ) result[i] -= interval; 
			        while (result[i] < FGeneration.left ) result[i] += interval;
			    }
		        return new FSolution(result);
			}
			
			public String getRepresentation() {
				String representation = "Generation[";
				for (FSolution solution: this)
					representation += "\n  " + solution.getRepresentation(); 
				return representation + "\n]";
			}


}
