package taboosearch.tsp
import core._

trait TspGenerationBuilder extends GenerationBuilder[TspSolution] {
	override def createGeneration() : TspGeneration = {
		return new TspGeneration();
	}
}