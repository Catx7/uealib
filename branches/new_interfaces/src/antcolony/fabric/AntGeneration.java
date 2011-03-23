package antcolony.fabric;
import java.util.ArrayList;

public class AntGeneration extends ArrayList<AntSolutionFabric> implements core.Generation<AntSolutionFabric> {

	private static final long serialVersionUID = 1L;

	private AntSolutionFabric solut;

	public AntGeneration(AntSolutionFabric s){
		this.solut = s;
	}
	
	public AntSolutionFabric getSolution(){
		return solut;
	}
	
	public AntGeneration getBest() {
		// TODO ! :)
		return this;
	}

}
