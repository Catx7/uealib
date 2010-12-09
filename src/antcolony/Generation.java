package antcolony;
import java.util.ArrayList;

public class Generation extends ArrayList<Solution> implements core.Generation<Solution> {

	private static final long serialVersionUID = 1L;

	private Solution solut;

	public Generation(Solution s){
		solut = s;
	}
	
	public Solution getSolution(){
		return solut;
	}
	
	public Generation getBest() {
		// TODO ! :)
		return this;
	}

}
