package taboosearch;

public abstract class Attribute<S extends Solution> {
	abstract public int hashCode();
	abstract public boolean equals(Object obj);
}
