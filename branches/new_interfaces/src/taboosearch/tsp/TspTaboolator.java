package taboosearch.tsp;

import java.util.HashMap;
import java.util.Vector;

import taboosearch.tenures.TenureStrategy;
import taboosearch.Taboolator;

public class TspTaboolator extends Taboolator<TspSolution> {
	private TenureStrategy strategy;
	private HashMap<Integer, Integer> taboo;
	
	public TspTaboolator(TenureStrategy strategy) {
		this.strategy = strategy;
		this.taboo = new HashMap<Integer, Integer>();
	}
	
	public void setTabu(final TspSolution s) {
		int v1 = s.getRoute().get(s.getMove().getI()),
			v2 = s.getRoute().get(s.getMove().getJ());
		taboo.remove(v1);
		taboo.remove(v2);
		taboo.put(v1, this.strategy.getTenure());
		taboo.put(v2, this.strategy.getTenure());
	}
	
	public boolean isTabu(final TspSolution s) {
		int v1 = s.getRoute().get(s.getMove().getI()),
			v2 = s.getRoute().get(s.getMove().getJ());
		boolean result = taboo.containsKey(v1) &&
						 taboo.containsKey(v2);
		return result;
	}
	
	public void tick() {
		strategy.tick();
		this.decreaseTenures();
	}
	
	private void decreaseTenures() {
		Vector<Integer> toErase = new Vector<Integer>();

		for (Integer key : taboo.keySet()) {
			int tenure = taboo.get(key);
			if (tenure == 1) {
				toErase.add(key);
			} else {
				taboo.put(key, tenure - 1);
			}
		}
		
		for (Integer key : toErase)
			taboo.remove(key);
	}
	
}
