package core

abstract class Initializator[+S <: Solution] {	
	def getInitialGeneration() : Generation[S];
}