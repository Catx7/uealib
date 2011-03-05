package readers.items;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import readers.Collection;

public abstract class KnapsackDataReader {

	public Collection readFromFile(String filename) {
			FileInputStream fi;
			try {
				fi = new FileInputStream(filename);
				Scanner s = new Scanner(fi, "utf-8"); 
				return getCollection(s);
			} catch (FileNotFoundException e) {
				System.err.println("Файл " + filename + " не найден!");
				e.printStackTrace();
			}
			return null;
		}
		
		abstract protected Collection getCollection(Scanner s);
		
}
