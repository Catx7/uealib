package readers.graphs;

import java.util.Scanner;

import readers.Graph;

/**
 * Читает полный граф, заданный матрицей весов.
 */
public class MatrixGraphReader extends GraphReader {

	@Override
	protected Graph readGraph(Scanner s) {
		int n = s.nextInt();
		double[][] matrix = new double[n][n];
		
		for ( int i = 0; i < n; ++i )
			for ( int j = 0; j < n; ++j )
				matrix[i][j] = s.nextDouble();

		Graph result = new Graph();
		result.setWeights(matrix);
		return result;
	}
	
}