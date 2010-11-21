package readers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class GraphReader {
	
	public Graph readFromFile(String filename) {
		FileInputStream fi;
		try {
			fi = new FileInputStream(filename);
			Scanner s = new Scanner(fi, "utf-8"); 
			return readGraph(s);
		} catch (FileNotFoundException e) {
			System.err.println("Файл " + filename + " не найден!");
			e.printStackTrace();
		}
		return null;
	}
	
	abstract protected Graph readGraph(Scanner s);
	
}
