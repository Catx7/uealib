package readers;

public class Graph {
	/**
	 * Матрица весов.
	 */
	private double[][] m;

	/**
	 * Устанавливает матрицу весов.
	 * @param m
	 */
	public void setWeights(double[][] m) {
		this.m = m;
	}

	/**
	 * Возвращает матрицу весов.
	 */
	public double[][] getWeights() {
		return m;
	}
	
}
