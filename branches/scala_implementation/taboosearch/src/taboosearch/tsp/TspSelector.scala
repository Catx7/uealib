package taboosearch.tsp
import taboosearch._
import core._

class TspSelector extends taboosearch.Selector[TspSolution] with TspGenerationBuilder {
		/*def keepTheBestSolutions(generation : Generation[TspSolution],
							 	 ctx : taboosearch.Context[TspSolution]) : Generation[TspSolution] = {
			return super.keepTheBestSolutions(generation, ctx);
		}*/
}