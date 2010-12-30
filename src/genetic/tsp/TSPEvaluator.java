package genetic.tsp;

import readers.Graph;

public class TSPEvaluator implements genetic.Evaluator<SalesmanRoute> {

	private double[][] graphWeights;

	public TSPEvaluator(Graph graph) {
		graphWeights = graph.getWeights();
	}

	@Override
	public int compare(SalesmanRoute o1, SalesmanRoute o2) {
		double o1fitness = evaluate(o1);
		double o2fitness = evaluate(o2);
		if (Math.abs(o1fitness - o2fitness) < 0.0000001)
			return 0;
		if (o1fitness > o2fitness)
			return 1;
		return -1;
	}

	@Override
	public double evaluate(SalesmanRoute route) {
		int[] tour = route.getTour();
		double sum = 0;
		for (int i = 0; i < tour.length - 1;)
			sum += graphWeights[tour[i]][tour[++i]];
		sum += graphWeights[tour[tour.length - 1]][tour[0]];
		double fitness = 10000. / sum;
		return fitness;
	}

	public double getWeight(SalesmanRoute route) {
		int[] tour = route.getTour();
		double sum = 0;
		for (int i = 0; i < tour.length - 1;)
			sum += graphWeights[tour[i]][tour[++i]];
		sum += graphWeights[tour[tour.length - 1]][tour[0]];
		return sum;
	}

}
