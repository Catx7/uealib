package common;

public class Pair<T, S> { // immutable
	public Pair(T f, S s) { 
		first = f;
		second = s;   
	}

	public T getFirst() {
		return first;
	}

	public S getSecond() {
		return second;
	}

	public String toString() { 
		return "(" + first.toString() + ", " + second.toString() + ")"; 
	}

	private T first;
	private S second;
}
