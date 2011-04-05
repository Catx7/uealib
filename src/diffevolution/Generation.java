package diffevolution;

import java.util.ArrayList;

public abstract class Generation<S extends diffevolution.Solution> extends ArrayList<S>
		implements core.Generation<S> {

	private static final long serialVersionUID = 1L;

	@Override
	abstract public Generation<S> getBest();
	
	abstract public S mutate(S chromosome1, S chromosome2, S chromosome3, int F);
	
	abstract public String getRepresentation();
	
}