package taboosearch;

import java.util.List;

public interface Move<S extends Solution> {
	public S operateOn(S solution);
	public List<? extends Attribute<S>> getAttributes(S solution);
}