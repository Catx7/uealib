package taboosearch.permutations;

public class Attribute<S extends taboosearch.permutations.Solution> extends taboosearch.Attribute<S> {
	Integer vertexNumber;
	
	public Attribute(int vertexNumber) {
		this.vertexNumber = vertexNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((vertexNumber == null) ? 0 : vertexNumber.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attribute<S> other = (Attribute<S>) obj;
		if (vertexNumber == null) {
			if (other.vertexNumber != null)
				return false;
		} else if (!vertexNumber.equals(other.vertexNumber))
			return false;
		return true;
	}
}
