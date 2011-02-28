package taboosearch;

import java.util.HashMap;
import java.util.LinkedList;

import taboosearch.Solution;
import taboosearch.tenures.TenureStrategy;

public class Taboolator<S extends Solution, M extends Move<S>> implements Tickable<S, M> {
	protected TenureStrategy strategy;
	protected HashMap<Attribute<S>, Integer> tenures;
	
	public Taboolator(TenureStrategy strategy) {
		this.strategy = strategy;
		this.tenures = new HashMap<Attribute<S>, Integer>();
	}
	
	public boolean isTabu(final S solution, final M move) {
		boolean isTabu = true;
		for (Attribute<S> attribute : move.getAttributes(solution))
			isTabu &= tenures.containsKey(attribute);
		return isTabu;
	}

	public void setTabu(final S solution, final M move) {
		for (Attribute<S> attribute : move.getAttributes(solution))
			tenures.put(attribute, strategy.getTenure());
	}
	
    public void tick(final S currentSolution, final M selectedMove,
    		final S nextSolution /* unused */, double bestCostEver /* unused */) {
        setTabu(currentSolution, selectedMove);
        strategy.tick();
        decreaseTenures();
    }
	
	private void decreaseTenures() {
		LinkedList<Attribute<S>> toErase = new LinkedList<Attribute<S>>();

		for (Attribute<S> attribute : tenures.keySet()) {
			int tenure = tenures.get(attribute);
			if (tenure == 1) {
				toErase.add(attribute);
			} else {
				tenures.put(attribute, tenure - 1);
			}
		}
		
		for (Attribute<S> attribute : toErase)
			tenures.remove(attribute);
	}
}
