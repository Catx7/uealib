package readers.items;

import java.util.Scanner;

import readers.KnapsackTask;

/**
 * Формат данных: количество предметов 
 * 				  ограничение 
 * 				  вес полезность
 * 
 */

public class KnapsackTestReader extends KnapsackDataReader{

	@Override
	protected KnapsackTask getKnapsackTask(Scanner s) {
		
		int n = s.nextInt();
		double V = s.nextDouble();
		Item[] matrix = new Item[n]; 
		KnapsackTask c = new KnapsackTask();
		
		for ( int i = 0; i < n; ++i ) {
			double w = s.nextDouble();
			double v = s.nextDouble();
		
			matrix[i] = new Item(v, w);
		}
		c.setItems(matrix);
		c.setCapacity(V);
		
		return c;
	}
	
}
