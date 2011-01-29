package core

abstract trait GenerationBuilder[+S <: Solution] {
	def createGeneration() : Generation[S]
}