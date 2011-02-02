package taboosearch.tsp;

public class TspSwapMove {
	// immutable
	
	private int i, j;
	
	public TspSwapMove(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public int getI() {
		return this.i;
	}
	
	public int getJ() {
		return this.j;
	}
	
	public String toString() {
		return "( " + i + ", " + j + " )";
	}
	
}
