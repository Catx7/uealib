package antcolony;
import java.util.ArrayList;


public class Generation extends ArrayList<AntSolution> implements core.Generation<AntSolution>{

	private static final long serialVersionUID = 1L;

	private AntSolution solut;

	public Generation(AntSolution s){
		this.solut = s;
	}
		
	public AntSolution getSolution(){
		return this.solut;
	}
	
	public Generation getBest() {
		// TODO ! :)
		return this;
	}

	
}
