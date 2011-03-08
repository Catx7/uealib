package taboosearch.knapsack;

import java.util.LinkedList;
import java.util.List;

public class KPMove implements taboosearch.Move<KPSolution> {
	public Integer add;
	public Integer drop;
	public enum Action { ADD, DROP };
	
	public KPMove(Action action, int item) {
		switch (action) {
			case ADD:
				this.add = item;
				this.drop = null;
				break;
			case DROP:
				this.add = null;
				this.drop = item;
				break;
		}
	}
	
	public KPMove(int add, int drop) {
		this.add = add;
		this.drop = drop;
	}
	
	public Integer getBeingAddedItem() {
		return add;
	}
	
	public Integer getBeingDroppedItem() {
		return drop;
	}
	
	public List<? extends taboosearch.Attribute<KPSolution>> getAttributesForIsTabu(KPSolution solution) {
		List<Attribute<KPSolution>> res = new LinkedList<Attribute<KPSolution>>();
		if (add != null) {
			res.add(new Attribute<KPSolution>(add));
		}
		return res;
	}
	
	@Override
	public List<? extends taboosearch.Attribute<KPSolution>> getAttributes(KPSolution solution) {
		List<Attribute<KPSolution>> res = new LinkedList<Attribute<KPSolution>>();
		if (drop != null) {
			res.add(new Attribute<KPSolution>(drop));
		}
		return res;
	}
	
	@Override
	public KPSolution operateOn(KPSolution solution) {
		KPSolution res = (KPSolution)solution.copy();
		if (drop != null)
		res.items.remove(drop);
		if (add != null)
		res.items.add(add);
		return res;
	}
}
