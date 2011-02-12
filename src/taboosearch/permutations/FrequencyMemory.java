package taboosearch.permutations;

import readers.Graph;

public class FrequencyMemory<S extends Solution, M extends Move<S>>
					extends taboosearch.FrequencyMemory<S, M> {
	
	int[][] residence; //первый индекс — вершина, второй — место в решении
	int[][] transition;
	int transitionsNumber; //общее число итераций
	
	public FrequencyMemory(Graph graph) {
		transitionsNumber = 0;
		int n = graph.getVertexesNumber();
		residence = new int[n][n];
		transition = new int[n][n];
		
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j)
				residence[i][j] = transition[i][j] = 0;
	}
	
	public void tick(S solution, M move) {
		for (int i = 0; i < solution.length(); ++i)
			residence[ solution.get(i) ][ i ]++;
		transition[solution.get(move.getI())][solution.get(move.getJ())] += 1;
		transitionsNumber++;
	}
	
	public double getPenalty(S solution, M move) {
		int i = move.getI(),
			j = move.getJ();
		return ((double) transition[solution.get(i)][solution.get(j)]) /
			   (transitionsNumber + 1);
	}

}
