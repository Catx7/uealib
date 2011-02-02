package taboosearch;

import taboosearch.Solution;

public abstract class Taboolator<S extends Solution> {
	
	abstract public void setTabu(final S s);
	abstract public boolean isTabu(final S s);
	abstract public void tick();
}
