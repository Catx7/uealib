package antcolony.fabric;

public class BackpackGeneration extends antcolony.fabric.GenerationFabric{

	private double capacity = 0;
	private double[] ves;
	
	public BackpackGeneration(AntSolutionFabric s, double cap, double[] v) {
		solut = s;
		this.capacity = cap;
		this.ves = v;
		// TODO Auto-generated constructor stub
	}

	public double getCapacity(){
		return this.capacity;
	}
	
	public double[] getWeight(){
		return this.ves;
	}
	
	/**
	 * 
	 */
	
}
