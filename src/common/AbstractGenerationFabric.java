package common;

import core.Generation;
import core.Solution;

public abstract class AbstractGenerationFabric<S extends Solution, G extends Generation<S>> {
	abstract public G makeGeneration();
}