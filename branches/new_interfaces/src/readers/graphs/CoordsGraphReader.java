package readers.graphs;

import java.util.Scanner;

import readers.Graph;
import readers.graphs.points.Point2D;

/**
 * Читает полный граф, заданный координатами вершин в двухмерном пространстве.
 * Первая строка файла должна содержать число вершин, последующие --
 * координаты вершин, разделенные пробелами.
 */
public class CoordsGraphReader extends GraphReader {
	
	@Override
	protected Graph readGraph(Scanner s) {
		int n = s.nextInt();
		Point2D[] points = new Point2D[n];
		
		for (int i = 0; i < n; ++i) {
			double x = s.nextDouble(),
				   y = s.nextDouble();

			points[i] = new Point2D(x, y);
		}

		double[][] matrix = this.computeWeights(points);
		
		Graph result = new Graph();
		result.setWeights(matrix);
		return result;
	}
	
	protected double[][] computeWeights(Point2D[] points) {
		int n = points.length;
		double[][] matrix = new double[n][n];
		
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j)
				matrix[i][j] = points[i].distanceTo(points[j]);
		
		return matrix;
	}
}
