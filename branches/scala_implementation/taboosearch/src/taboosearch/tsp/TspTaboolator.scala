package taboosearch.tsp

import core._;
import taboosearch.tenures._
import taboosearch._;
import scala.collection.mutable.{ListBuffer, HashMap};

class TspTaboolator(val strategy : TenureStrategy) extends Taboolator[TspSolution] {
	val taboo = new HashMap[Int, Int]; // vertex -> tenure
	
	def setTabu(solution : TspSolution) = {
		val (i, j) = solution.move;
		val v1 = solution(i);
		val v2 = solution(j);
		val currentTenure = strategy.getTenure(); 
		taboo.put(v1, currentTenure);
		taboo.put(v2, currentTenure);
	}
	
	def isTabu(solution : TspSolution) : Boolean = {
		val (i, j) = solution.move;
		val v1 = solution(i);
		val v2 = solution(j);
		return taboo.contains(v1) && taboo.contains(v2);
	}
	
	def tick() = {
		strategy.tick();
		decreaseTenures();
	}
	
	private def decreaseTenures() = {
		var toErase = new ListBuffer[Int]();
		for ((attr, tenure) <- taboo) {
			if (tenure == 1)
				toErase.append(attr);
			else {
				taboo.remove(attr);
				taboo.put(attr, tenure - 1);
			}
		}
		taboo --= toErase;
	}
	
}