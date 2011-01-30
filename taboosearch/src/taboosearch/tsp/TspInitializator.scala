package taboosearch.tsp

import scala.collection.mutable.ListBuffer;
import core._

class TspInitializator(var graph : Graph) extends Initializator[TspSolution] {

	def getSolution(startFrom : Int) : List[Int] = {
		val n = graph.weights.length;
		val route = new ListBuffer[Int]();
		var v = startFrom;
		var nearestV = startFrom;
		route.append(v);
		
		for (times <- 1 until n) {
			var min = Double.MaxValue;
			for (w <- 0 until n) {
				if (graph.weights(v)(w) < min && !route.contains(w)) {
				    min = graph.weights(v)(w);
				    nearestV = w;
				}
			}
			route.append(nearestV);
			v = nearestV;
		}
		
		return route.toList;
	}

	def getInitialGeneration() : TspGeneration = {
		val result = new TspGeneration();
		val route = getSolution(0);
		result += new TspSolution(route);
		return result;
	}
	
}