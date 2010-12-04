package taboosearch;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import taboosearch.tenures.TenureStrategy;

public class Taboolator {
	
	private TenureStrategy strategy;
	private HashMap<Integer, Integer> taboo; // vertex -> tenure
	
	public Taboolator(TenureStrategy strategy) {
		this.strategy = strategy;
		this.taboo = new HashMap<Integer, Integer>();
	}
	
	public void setTabu(final LazySolution s) {
		int v1 = s.getSolution().get( s.getMove().getI() );
		int v2 = s.getSolution().get( s.getMove().getJ() );
		taboo.remove(v1);
		taboo.remove(v2);
		taboo.put(v1, this.strategy.getTenure());
		taboo.put(v2, this.strategy.getTenure());
	}
	
	public boolean isTabu(final LazySolution s) {
		int v1 = s.getSolution().get( s.getMove().getI() );
		int v2 = s.getSolution().get( s.getMove().getJ() );
		boolean res = taboo.containsKey(v1) &&
					  taboo.containsKey(v2);
		return res;
	}
	
	public void tick() {
		strategy.tick();
		//System.out.println("TICK " + this.taboo.size());
		this.decreaseTenures();
	}
	
	private void decreaseTenures() {
		Vector<Integer> toErase = new Vector<Integer>();
		Iterator<Integer> iterator = taboo.keySet().iterator();

		while (iterator.hasNext()) {
			Integer move = iterator.next();
			int tenure = taboo.get(move);
			if (tenure == 1) {
				toErase.add(move);
			} else {
				taboo.put(move, tenure - 1);
			}
		}
		
		iterator = toErase.iterator();
		while (iterator.hasNext()) {
			Integer move = iterator.next();
			taboo.remove(move);
		}
	}
	
}
