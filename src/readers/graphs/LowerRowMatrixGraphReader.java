package readers.graphs;

import java.util.Scanner;

import readers.Graph;

public class LowerRowMatrixGraphReader extends GraphReader {
	
	@Override
	protected Graph readGraph(Scanner s) {
		int n = s.nextInt();
		double[][] matrix = new double[n][n];
		
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < i + 1; ++j) {
				int w = s.nextInt();
				matrix[i][j] = w;
				matrix[j][i] = w;
			}				
		}

		Graph result = new Graph();
		result.setWeights(matrix);
		return result;
	}

}
