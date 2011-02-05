package taboosearch.tsp;

import java.util.HashMap;
import java.util.Vector;

import taboosearch.tenures.TenureStrategy;
import taboosearch.Taboolator;

public class TSPTaboolator extends Taboolator<TSPSolution, TSPSwapMove> {
	private TenureStrategy strategy;
	private HashMap<TSPAttribute, Integer> taboo;
	
	public TSPTaboolator(TenureStrategy strategy) {
		this.strategy = strategy;
		this.taboo = new HashMap<TSPAttribute, Integer>();
	}
		
	@Override
	public boolean isTabu(final TSPSolution solution, final TSPSwapMove move) {
		boolean isTabu = true;
		for (TSPAttribute attribute : move.getAttributes(solution))
			isTabu &= taboo.containsKey(attribute);
		return isTabu;
	}

	@Override
	public void setTabu(final TSPSolution solution, final TSPSwapMove move) {
		for (TSPAttribute attribute : move.getAttributes(solution))
			taboo.put(attribute, strategy.getTenure());
	}
	
	public void tick() {
		strategy.tick();
		decreaseTenures();
	}
	
	public void tick(final TSPSolution solution, final TSPSwapMove move) {
		setTabu(solution, move);
		strategy.tick();
		decreaseTenures();
	}
	
	private void decreaseTenures() {
		Vector<TSPAttribute> toErase = new Vector<TSPAttribute>();

		for (TSPAttribute attribute : taboo.keySet()) {
			int tenure = taboo.get(attribute);
			if (tenure == 1) {
				toErase.add(attribute);
			} else {
				taboo.put(attribute, tenure - 1);
			}
		}
		
		for (TSPAttribute attribute : toErase)
			taboo.remove(attribute);
	}
}
