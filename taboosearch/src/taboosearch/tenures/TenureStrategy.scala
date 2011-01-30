package taboosearch.tenures

abstract class TenureStrategy {
	def tick();
	def getTenure() : Int;
}