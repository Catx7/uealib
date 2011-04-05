package readers.items;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

import readers.KnapsackTask;

public abstract class KnapsackDataReader {

	public KnapsackTask readFromFile(String filename) {
			FileInputStream fi;
			try {
				fi = new FileInputStream(filename);
				Scanner s = new Scanner(fi, "utf-8");
				s.useLocale(Locale.US);
				return getKnapsackTask(s);
			} catch (FileNotFoundException e) {
				System.err.println("���� " + filename + " �� ������!");
				e.printStackTrace();
			}
			return null;
		}
		
		abstract protected KnapsackTask getKnapsackTask(Scanner s);
		
}
