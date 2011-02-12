package common;

import taboosearch.Generation;
import taboosearch.Solution;

public abstract class AbstractGenerationFabric<S extends Solution, G extends Generation<S>> {
	abstract public G makeGeneration();
}