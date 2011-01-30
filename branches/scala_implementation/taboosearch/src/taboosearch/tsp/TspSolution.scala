package taboosearch.tsp

import scala.collection.immutable
import core._
// http://www.scala-lang.org/api/current/scala/collection/immutable/IndexedSeq.html


/* "Ленивое" решение задачи коммивояжёра. Представляет собой пару (route, move),
 * где route -- маршрут коммивояжера, move -- индексы вершин, которые необходимо
 * помять местами в route.
 */
class TspSolution(val route : List[Int], val move : Pair[Int, Int])
							extends Solution with immutable.IndexedSeq[Int] {

	// Конструктор вида TspSolution(1, 2, 3, 4, 5)
	def this(route : Int*) = this(route.toList, null)
	
	// Конструктор вида TspSolution(List(1, 2, 3, 4, 5))
	def this(route : List[Int]) = this(route, null)
	
	override def apply(idx : Int) = route(idx % route.length) // абстрактный метод в IndexedSeq
	
	override def length() = route.length // абстрактный метод в IndexedSeq
	
	override def copy() : TspSolution = new TspSolution(route, move);
	
	override def toString() : String = "[" + unlazify().mkString(", ") + " ]"
	
	def unlazify() : List[Int] = {
		if (move != null) {
			val routeCopy = route.toArray
			val (i, j) = move;		
			val tmp = routeCopy(i);
			routeCopy(i) = routeCopy(j);
			routeCopy(j) = tmp;
			return routeCopy.toList;
		} else {
			return route;
		}
	}
	
}