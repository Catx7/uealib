package taboosearch;

import java.util.Collection;

public interface Move<S extends Solution> extends core.alternative.Move<S> {
	public Collection<? extends Attribute<S>> getAttributes(S solution);
}
