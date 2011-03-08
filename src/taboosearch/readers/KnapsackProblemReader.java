package taboosearch.readers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class KnapsackProblemReader {
	public KnapsackProblem readFromFile(String filename) {
		FileInputStream fi;
		try {
			fi = new FileInputStream(filename);
			Scanner s = new Scanner(fi, "utf-8");
			s.useLocale(Locale.US);
			return readTask(s);
		} catch (FileNotFoundException e) {
			System.err.println("Файл " + filename + " не найден!");
			e.printStackTrace();
		}
		return null;
	}
	
	protected KnapsackProblem readTask(Scanner s) {
		int n = s.nextInt();
		double capacity = s.nextDouble();
		double[] values = new double[n];
		double[] weights = new double[n];
		
		for (int i = 0; i < n; ++i) {
			weights[i] = s.nextDouble();
			values[i] = -s.nextDouble();		
		}

		return new KnapsackProblem(weights, values, capacity);
	}
}
