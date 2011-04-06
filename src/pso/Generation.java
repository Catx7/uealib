package pso;

import java.util.ArrayList;

public abstract class Generation<S extends pso.Solution<S>> extends ArrayList<S>
		implements core.Generation<S> {

	private static final long serialVersionUID = 1L;

	//@Override
	abstract public Generation<S> getBest();
	
	//abstract public S mutate(S chromosome1, S chromosome2, S chromosome3);
	
	abstract public String getRepresentation();
	
}