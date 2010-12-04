package taboosearch.tenures;

public class ConstantTenureStrategy implements TenureStrategy {
	
	private final int tenure;
	
	public ConstantTenureStrategy(int tenure) {
		this.tenure = tenure;
	}
	
	public int getTenure() {
		return this.tenure;
	}

	public void tick() {}
	
}
