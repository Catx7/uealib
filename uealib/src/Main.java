
public class Main {

	public static void main(String[] args) {
		
		int[] a1 = {1,2,3};
		int[] a2 = {1,2,3,4};
		ts.Solution b = new ts.Solution(a1);
		ts.Solution c = new ts.Solution(a2);
		System.out.println(b.compareTo(c));
	}

}
