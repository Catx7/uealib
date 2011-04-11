package usablefunctions;

public interface Function {
	public double apply(double[] xs); // xs -- точка в пространстве Rn
	
	/* Область определения функции. Возвращает массив размера n (n -- размерность
	 * пространства, на котором определена функция), i-тый элемент которого
	 * представляет собой пару (a_i, b_i), которая накладывает на x_i ограничение
	 * следующего вида: a_i < x_i < b_i; 
	 */
	public double[][] getDomain();
}
