package core

abstract class TenureStrategy {
	def tick();
	def getTenure() : Int;
}