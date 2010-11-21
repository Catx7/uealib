package readers;

public class Graph {
	/**
	 * Матрица весов.
	 */
	private int[][] m;

	/**
	 * Устанавливает матрицу весов.
	 * @param m
	 */
	public void setWeights(int[][] m) {
		this.m = m;
	}

	/**
	 * Возвращает матрицу весов.
	 */
	public int[][] getWeights() {
		return m;
	}
	
}
