package readers.graphs;

import java.util.Scanner;

import readers.Graph;

public class UpperRowMatrixGraphReader extends GraphReader {
	
	@Override
	protected Graph readGraph(Scanner s) {
		int n = s.nextInt();
		double[][] matrix = new double[n][n];
		
		for ( int i = n; i > 0; --i ) {
			for ( int j = n - i + 1; j < n; ++j ) {
				int w = s.nextInt();
				matrix[n - i][j] = w;
				matrix[j][n - i] = w;
			}				
		}

		Graph result = new Graph();
		result.setWeights(matrix);
		return result;
	}

}
