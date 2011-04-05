package diffevolution;


public class SimpleArrayCrossover<S extends ArraySolution> implements diffevolution.AbstractCrossoverFabric<S>{
	
	private double Cr = 0.8;
	
	public void doCrossover(S probe, S donor) {
		double Cr = this.getCr();
        for(int i = 0; i < donor.length(); ++i)
        	probe.set(i, (Math.random() < Cr)? probe.get(i) : donor.get(i));
	}
	
	public void setCr(double Cr) {
		this.Cr = Cr;
	}
	
	public double getCr() {
		return this.Cr;
	}
}
