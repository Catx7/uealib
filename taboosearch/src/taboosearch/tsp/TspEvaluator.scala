package taboosearch.tsp

import core._

class TspEvaluator(val graph : Graph) extends Evaluator[TspSolution] {
	
	def evaluate(route : List[Int]) : Double = {
		val n = route.length;
		var value = 0.0;
		var v = route(0);
		for (i <- 1 until (n + 1)) {
			var w = route(i % route.length);
			value += graph.weights(v)(w);
			v = w;
		}
		return value;
	}
	
	def evaluate(solution : TspSolution) : Double = {
		if (solution.move == null) {
			return evaluate(solution.route);
		}
		val (i, j) = solution.move;
		val fitness = evaluate(solution.route);
		
		val v1 = solution(i);
		val v2 = solution(j);
		val t1 = solution(i - 1);
		val w1 = solution(i + 1);
		val t2 = solution(j + 1);
		val w2 = solution(j - 1);

		val weights = graph.weights;
		val delta : Double = 
			if (Math.abs((i - j) % solution.route.length) == 1)
				(- weights(t1)(v1) - weights(v2)(t2)
				 + weights(t1)(v2) + weights(v1)(t2));
			else
				(- weights(v1)(w1) - weights(w2)(v2)
			     - weights(t1)(v1) - weights(v2)(t2)
			     + weights(w2)(v1) + weights(v2)(w1)
			     + weights(v1)(t2) + weights(t1)(v2));
		return fitness + delta;
	}
}