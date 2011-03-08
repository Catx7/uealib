package taboosearch.knapsack;

public class Attribute<S extends KPSolution> extends taboosearch.Attribute<S> {
	Integer item;
	
	public Attribute(int vertexNumber) {
		this.item = vertexNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((item == null) ? 0 : item.hashCode());
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
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}
}
