package core.alternative;

import java.util.List;

import core.Solution;

public interface Move<S extends Solution> {
	public S operateOn(S solution);
}