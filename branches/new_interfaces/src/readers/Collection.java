package readers;

import readers.items.Item;

public class Collection {
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
	
	public double getConstrait() {
		return this.V;
	}

	public void setConstrait(double constrait) {
		this.V = constrait;		
	}
	
}
