package taboosearch;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Generation<S extends Solution> extends ArrayList<S> implements core.Generation<S> {
	public Generation() {
		super();
	}
	
	public Generation(S solution) {
		super();
		add(solution);		
	}
	
	@Override
	public core.Generation<S> getBest() {
		return null;
	}

}