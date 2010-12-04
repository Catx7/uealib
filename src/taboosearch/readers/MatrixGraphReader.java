package readers;

import java.util.Scanner;

/**
 * Читает полный граф, заданный матрицей весов.
 */
public class MatrixGraphReader extends GraphReader {

	protected Graph readGraph(Scanner s) {
		int n = s.nextInt();
		double[][] matrix = new double[n][n];
		double w;
		
		for ( int i = 0; i < n; ++i ) {
			for ( int j = 0; j < n; ++j ) {
				w = s.nextDouble();
				matrix[i][j] = w;
			}
		}
		
		Graph result = new Graph();
		result.setWeights(matrix);
		
		return result;
	}
	
}