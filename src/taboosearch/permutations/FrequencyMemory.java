package taboosearch.permutations;

import readers.Graph;

public class FrequencyMemory<S extends Solution, M extends Move<S>>
				extends taboosearch.FrequencyMemory<S, M> {
	
	private int[][] residence; //первый индекс — вершина, второй — место в решении
	private int[][] transition;
	private int transitionsNumber; //общее число итераций
	private double diversificationCoef;
	
	public FrequencyMemory(Graph graph, double diversificationCoef) {
		int n = graph.getVertexesNumber();
		this.residence = new int[n][n];
		this.transition = new int[n][n];
		
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j)
				residence[i][j] = transition[i][j] = 0;
		
		this.transitionsNumber = 0;
		this.diversificationCoef = diversificationCoef;
	}
	
	public void tick(S solution, M move) {
		int n = solution.length();
		for (int i = 0; i < n; ++i)
			residence[solution.get(i)][i]++;
		
		int v = solution.get(move.getI());
		int w = solution.get(move.getJ());
		transition[v][w] += 1;
		transition[w][v] += 1;
		transitionsNumber++;
	}
	
	public double getPenalty(S solution, M move) {
		int v = solution.get(move.getI());
		int w = solution.get(move.getJ());
		return diversificationCoef * ((double) transition[v][w]) / (transitionsNumber + 1);
	}

}
