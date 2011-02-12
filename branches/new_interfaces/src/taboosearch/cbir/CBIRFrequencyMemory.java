package taboosearch.cbir;

import taboosearch.FrequencyMemory;
import taboosearch.readers.FeaturesSpace;

public class CBIRFrequencyMemory extends FrequencyMemory<CBIRSolution, CBIRSwapMove> {
	int[][] residence; //первый индекс — вершина, второй — место в решении
	int[][] transition;
	int transitionsNumber; //общее число итераций
	
	public CBIRFrequencyMemory(FeaturesSpace space) {
		transitionsNumber = 0;
		int n = space.n;
		residence = new int[n][n];
		transition = new int[n][n];
		
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j)
				residence[i][j] = transition[i][j] = 0;
	}
	
	public void tick(CBIRSolution solution, CBIRSwapMove move) {
		for (int i = 0; i < solution.length(); ++i)
			residence[ solution.get(i) ][ i ]++;
		transition[solution.get(move.getI())][solution.get(move.getJ())] += 1;
		transitionsNumber++;
	}
	
	public double getPenalty(CBIRSolution solution, CBIRSwapMove move) {
		int i = move.getI(),
			j = move.getJ();
		return ((double) transition[solution.get(i)][solution.get(j)]) /
			   (transitionsNumber + 1);
	}
}
