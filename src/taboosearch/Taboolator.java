package taboosearch;

import taboosearch.Solution;

public abstract class Taboolator<S extends Solution,
								 M extends Move<S>> {
	
	abstract public void setTabu(final S s, final M m);
	abstract public boolean isTabu(final S s, final M m);
	abstract public void tick();
	abstract public void tick(final S s, final M m);
	
}
