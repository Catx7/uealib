package taboosearch;

import java.util.HashMap;
import java.util.LinkedList;

import taboosearch.Solution;
import taboosearch.tenures.TenureStrategy;

public class Taboolator<S extends Solution, M extends Move<S>> {
	
	protected TenureStrategy strategy;
	protected HashMap<Attribute<S>, Integer> taboo;
	
	public Taboolator(TenureStrategy strategy) {
		this.strategy = strategy;
		this.taboo = new HashMap<Attribute<S>, Integer>();
	}
	
	public boolean isTabu(final S solution, final M move) {
		boolean isTabu = true;
		for (Attribute<S> attribute : move.getAttributes(solution))
			isTabu &= taboo.containsKey(attribute);
		return isTabu;
	}

	public void setTabu(final S solution, final M move) {
		for (Attribute<S> attribute : move.getAttributes(solution))
			taboo.put(attribute, strategy.getTenure());
	}
	
	public void tick(final S solution, final M move) {
		setTabu(solution, move);
		strategy.tick();
		decreaseTenures();
	}
	
	private void decreaseTenures() {
		LinkedList<Attribute<S>> toErase = new LinkedList<Attribute<S>>();

		for (Attribute<S> attribute : taboo.keySet()) {
			int tenure = taboo.get(attribute);
			if (tenure == 1) {
				toErase.add(attribute);
			} else {
				taboo.put(attribute, tenure - 1);
			}
		}
		
		for (Attribute<S> attribute : toErase)
			taboo.remove(attribute);
	}
		
}
