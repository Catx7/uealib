package taboosearch;

import java.util.ArrayList;

@SuppressWarnings("serial")
public abstract class Generation<S extends Solution> extends ArrayList<S> implements core.Generation<S> {

	@Override
	public core.Generation<S> getBest() {
		return null;
	}

}