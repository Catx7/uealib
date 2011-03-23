package antcolony.fabric;
import java.util.ArrayList;


public abstract class GenerationFabric extends ArrayList<AntSolutionFabric> implements core.Generation<AntSolutionFabric>{

	private static final long serialVersionUID = 1L;

	protected AntSolutionFabric solut;

	/*public Generation(AntSolution s){
		this.solut = s;
	}*/
		
	public AntSolutionFabric getSolution(){
		return this.solut;
	}
	
	public GenerationFabric getBest() {
		// TODO ! :)
		return this;
	}
	
	public abstract double getCapacity();
	
	public abstract double[] getWeight();

	
}
