package diffevolution;

import java.util.ArrayList;
import diffevolution.Solution;

public class Generation extends ArrayList<Solution> implements core.Generation<Solution> {

	public Generation getBest() {
		return this;
	}
	
}
