package readers;

import readers.items.Item;

public class KnapsackTask {
	private Item[] items;	// items[utility][weight]
	private double V;			// limit
	
	public void setItems(Item[] items) {
		this.items = items;
	}	
	
	public Item[] getItems() {
		return items;
	}
	
	public int getItemsNumber() {
		return this.items.length;
	}
	
	public double getCapacity() {
		return this.V;
	}

	public void setCapacity(double capacity) {
		this.V = capacity;		
	}
	
}
