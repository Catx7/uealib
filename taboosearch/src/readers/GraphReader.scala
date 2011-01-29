package core
import java.util.Scanner;
import java.io.{FileInputStream, FileNotFoundException};

abstract class GraphReader {
	
	def readFromFile(filename : String) : Graph = {
		try {
			val fi = new FileInputStream(filename);
			val scanner = new Scanner(fi, "utf-8"); 
			return readGraph(scanner);
		} catch {
			case e : FileNotFoundException => {
				println("Файл " + filename + " не найден!");
				e.printStackTrace();
			}
		}
		return null;
	}
	
	def readGraph(s : Scanner) : Graph;
	
}