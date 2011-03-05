package readers.items;

import java.util.Scanner;

import readers.Collection;

/**
 * Формат данных: количество предметов 
 * 				  ограничение 
 * 				  полезность вес
 * 
 */

public class TestReader extends KnapsackDataReader{

	@Override
	protected Collection getCollection(Scanner s) {
		int n = s.nextInt();
		double V = s.nextDouble();
		Item[] matrix = new Item[n]; 
		Collection c = new Collection();
		
		for ( int i = 0; i < n; ++i )
			matrix[i] = new Item(s.nextDouble(), s.nextDouble());
		
		c.setItems(matrix);
		c.setConstrait(V);
		
		return c;
	}
	
}
