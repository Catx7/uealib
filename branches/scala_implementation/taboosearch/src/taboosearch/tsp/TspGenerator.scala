package taboosearch.tsp
import core._

class TspGenerator extends Generator[TspGeneration] {

	type Move = Pair[Int, Int]
	
	private def getAllMoves(n : Int) : Array[Move] = {
		val result = new Array[Move]((n - 2) * (n - 1) / 2);
		var idx = 0;
		for (i <- 1 until (n - 1))
			for (j <- (i + 1) until n) {
				result(idx) = new Move(i, j);
				idx += 1;
			}
		
		return result;
	}

	def getNext(generation : TspGeneration) : TspGeneration = {		
		val s = generation.head;
		val route = s.unlazify();
		var result = new TspGeneration();
		
		val moves = getAllMoves(route.length);
		for (move <- moves)
			result += new TspSolution(route, move);
		
		return result;
	}
	
}