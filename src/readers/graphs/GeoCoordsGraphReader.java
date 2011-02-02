package readers.graphs;

import readers.graphs.points.Point2D;
import readers.graphs.points.PointGeo;

/**
 * Читает полный граф, заданный географическими координатами вершин на Земле.
 * Первая строка файла должна содержать число вершин, последующие -- координаты
 * вершин, разделенные пробелами.
 */
public class GeoCoordsGraphReader extends CoordsGraphReader {

	@Override
	protected double[][] computeWeights(Point2D[] points) {
		int n = points.length;
		double[][] matrix = new double[n][n];

		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j) {
				PointGeo x = new PointGeo(points[i]);
				PointGeo y = new PointGeo(points[j]);
				matrix[i][j] = x.distanceTo(y);
			}

		return matrix;
	}

}
