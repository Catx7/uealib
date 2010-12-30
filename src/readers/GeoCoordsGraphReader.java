package readers;

import java.util.Scanner;

/**
 * Читает полный граф, заданный географическими координатами вершин на Земле.
 * Первая строка файла должна содержать число вершин, последующие -- координаты
 * вершин, разделенные пробелами.
 */
public class GeoCoordsGraphReader extends GraphReader {

	protected Graph readGraph(Scanner s) {
		int n = s.nextInt();
		double[][] coords = new double[n][2];

		for (int i = 0; i < n; ++i) {
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

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				matrix[i][j] = distance(coords[i], coords[j]);
			}
		}

		return matrix;
	}

	private double degToRad(double coord) {
		double deg = ((int) coord) + (coord - (int) coord) * 5. / 3.;
		return Math.PI * deg / 180.;
	}

	/**
	 * Возвращает расстояние между двумя точками
	 */
	private int distance(double[] a, double[] b) {
		double a0r = degToRad(a[0]);
		double b0r = degToRad(b[0]);
		double a1r = degToRad(a[1]);
		double b1r = degToRad(b[1]);
		double c1 = Math.cos(a1r - b1r);
		double c2 = Math.cos(a0r - b0r);
		double c3 = Math.cos(a0r + b0r);
		return (int) (1. + 6378.388 * Math
				.acos(.5 * ((1. + c1) * c2 - (1. - c1) * c3)));
	}

}
