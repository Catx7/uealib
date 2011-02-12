package taboosearch.cbir;

import taboosearch.tenures.TenureStrategy;
import taboosearch.Taboolator;

public class CBIRTaboolator extends Taboolator<CBIRSolution, CBIRSwapMove> {

	public CBIRTaboolator(TenureStrategy strategy) {
		super(strategy);
	}
	/*private TenureStrategy strategy;
	private HashMap<CBIRAttribute, Integer> taboo;
	
	public CBIRTaboolator(TenureStrategy strategy) {
		this.strategy = strategy;
		this.taboo = new HashMap<CBIRAttribute, Integer>();
	}
		
	@Override
	public boolean isTabu(final CBIRSolution solution, final CBIRSwapMove move) {
		boolean isTabu = true;
		for (CBIRAttribute attribute : move.getAttributes(solution))
			isTabu &= taboo.containsKey(attribute);
		return isTabu;
	}

	@Override
	public void setTabu(final CBIRSolution solution, final CBIRSwapMove move) {
		for (CBIRAttribute attribute : move.getAttributes(solution))
			taboo.put(attribute, strategy.getTenure());
	}
	
	public void tick() {
		strategy.tick();
		decreaseTenures();
	}
	
	public void tick(final CBIRSolution solution, final CBIRSwapMove move) {
		setTabu(solution, move);
		strategy.tick();
		decreaseTenures();
	}
	
	private void decreaseTenures() {
		Vector<CBIRAttribute> toErase = new Vector<CBIRAttribute>();

		for (CBIRAttribute attribute : taboo.keySet()) {
			int tenure = taboo.get(attribute);
			if (tenure == 1) {
				toErase.add(attribute);
			} else {
				taboo.put(attribute, tenure - 1);
			}
		}
		
		for (CBIRAttribute attribute : toErase)
			taboo.remove(attribute);
	}*/
}
