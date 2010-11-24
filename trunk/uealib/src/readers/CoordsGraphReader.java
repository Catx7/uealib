package readers;

import java.util.Scanner;

/**
 * Читает полный граф, заданный координатами вершин в двухмерном пространстве.
 * Первая строка файла должна содержать число вершин, последующие --
 * координаты вершин, разделенные пробелами.
 */
public class CoordsGraphReader extends GraphReader {
	

	protected Graph readGraph(Scanner s) {
		int n = s.nextInt();
		double[][] coords = new double[n][2];
		
		for ( int i = 0; i < n; ++i ) {
			double[] point = new double[2];
			point[0] = s.nextDouble();
			point[1] = s.nextDouble();
			coords[i] = point;
		}

		double[][] matrix = this.computeWeights(coords);

		Graph result = new Graph();
		result.setWeights(matrix);
		
		return result;
	}
	
	/**
	 * Вычисляет матрицу весов по списку вершин
	 */
	private double[][] computeWeights(double[][] coords) {
		int n = coords.length;
		double[][] matrix = new double[n][n];
		
		for ( int i = 0; i < n; ++i ) {
			for ( int j = 0; j < n; ++j ) {
				matrix[i][j] = distance(coords[i], coords[j]);
			}
		}
		
		return matrix;
	}
	
	/**
	 * Возвращает расстояние между двумя точками, округлённое до int
	 */
	private double distance(double[] a, double[] b) {
		return Math.sqrt( Math.pow(b[0] - a[0], 2) + Math.pow(b[1] - a[1], 2) );
	}

}
