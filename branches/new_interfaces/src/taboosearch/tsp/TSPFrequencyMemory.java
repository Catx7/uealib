package taboosearch.tsp;

import readers.Graph;
import taboosearch.FrequencyMemory;

public class TSPFrequencyMemory extends FrequencyMemory<TSPSolution, TSPSwapMove> {
	int[][] residence; //первый индекс — вершина, второй — место в решении
	int[][] transition;
	int transitionsNumber; //общее число итераций
	
	public TSPFrequencyMemory(Graph graph) {
		transitionsNumber = 0;
		int n = graph.getVertexesNumber();
		residence = new int[n][n];
		transition = new int[n][n];
		
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j)
				residence[i][j] = transition[i][j] = 0;
	}
	
	public void tick(TSPSolution solution, TSPSwapMove move) {
		for (int i = 0; i < solution.length(); ++i)
			residence[ solution.get(i) ][ i ]++;
		transition[solution.get(move.getI())][solution.get(move.getJ())] += 1;
		transitionsNumber++;
	}
	
	public double getPenalty(TSPSolution solution, TSPSwapMove move) {
		int i = move.getI(),
			j = move.getJ();
		return ((double) transition[solution.get(i)][solution.get(j)]) /
			   (transitionsNumber + 1);
	}
}
