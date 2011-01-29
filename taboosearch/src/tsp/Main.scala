package tsp
import core._

object Main {
	def main(args: Array[String]) = {
		
		val graph = (new CoordsGraphReader()).readFromFile("/home/rrhu/workspace/uealib/graphs/burma14.txt");
		var i 	= new TspInitializator(graph);
		var g 	= new TspGenerator();
		var tc 	= new common.UnconditionalTransition();
		var sc 	= new common.TicksStoppingCriteria(2000);
		var s 	= new TspSelector();
		var ev 	= new TspEvaluator(graph);
		var tr 	= new TspTaboolator(new common.ConstantTenureStrategy(5));
		
		var initialGeneration = i.getInitialGeneration();
		var initialSolution = initialGeneration.head;
		var initialSolutionFitness = ev.evaluate(initialSolution);
		
		val c = new Context(ev, tr, initialSolution, initialSolutionFitness);
	
		println("Result:");
		val resultGeneration = (new TspAlgorithm(i, g, sc, tc, s, c)).solve(initialGeneration);
		println(c.bestSolutionEver);
		println(c.bestSolutionEverFitness);
		
		println("Exact solution:");
		var exactSolution = new TspSolution(0, 1, 13, 2, 3, 4, 5, 11, 6, 12, 7, 10, 8, 9);
		println(exactSolution);
		println(ev.evaluate(exactSolution));
		
	}
}