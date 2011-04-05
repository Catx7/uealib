package diffevolution;


public abstract class ArraySolution<T extends Number> extends diffevolution.Solution {

	abstract public String getRepresentation();
	abstract public T get(int index);
	abstract public void set(int index, T value);
	abstract public int length();

}