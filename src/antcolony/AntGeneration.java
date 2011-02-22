package antcolony;
import java.util.ArrayList;

public class AntGeneration extends ArrayList<AntSolution> implements core.Generation<AntSolution> {

	private static final long serialVersionUID = 1L;

	private AntSolution solut;

	public AntGeneration(AntSolution s){
		this.solut = s;
	}
	
	public AntSolution getSolution(){
		return solut;
	}
	
	public AntGeneration getBest() {
		// TODO ! :)
		return this;
	}

}
