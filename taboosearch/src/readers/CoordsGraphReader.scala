package core
import java.util.Scanner;

class CoordsGraphReader extends GraphReader {
	
	type Point = Pair[Double, Double]
	
	override def readGraph(s : Scanner) : Graph = {
		val n = s.nextInt();
		val coords = new Array[Point](n);
		
		for (i <- 0 until n) {
			val x = s.nextDouble();
			val y = s.nextDouble();
			coords(i) = new Point(x, y);
		}
		
		val weights = computeWeights(coords);		
		return new Graph(weights);
	}
	
	private def computeWeights(coords : Array[Point]) : Array[Array[Double]] = {
		val n = coords.length;
		val matrix = Array.ofDim[Double](n, n);
		for (i <- 0 until n) {
			for (j <- 0 until n) {
				val dist = distance(coords(i), coords(j));
				matrix(i)(j) = dist; 
				matrix(j)(i) = dist;
			}
		}
		return matrix;
	}
	
	private def distance(a : Point, b : Point) : Double = {
		return Math.sqrt(Math.pow(b._1 - a._1, 2) + Math.pow(b._2 - a._2, 2));
	}

}