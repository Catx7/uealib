package readers;

import java.util.Scanner;

public class MatrixGraphReader extends GraphReader {

	protected Graph readGraph(Scanner s) {
		int n = s.nextInt();
		int[][] matrix = new int[n][n];
		int w;
		
		for ( int i = 0; i < n; ++i ) {
			for ( int j = 0; j < n; ++j ) {
				w = s.nextInt();
				matrix[i][j] = w;
			}
		}
		
		Graph result = new Graph();
		result.setWeights(matrix);
		
		return result;
	}
	
}