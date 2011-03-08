package taboosearch.knapsack;

import java.util.List;

import taboosearch.Attribute;
import taboosearch.Taboolator;
import taboosearch.tenures.TenureStrategy;

public class KPTaboolator extends Taboolator<KPSolution, KPMove> {

	public KPTaboolator(TenureStrategy strategy) {
		super(strategy);
	}
	
	@Override
	public boolean isTabu(final KPSolution solution, final KPMove move) {
		List<? extends Attribute<KPSolution>> attributes = move.getAttributesForIsTabu(solution);
		boolean isTabu = attributes.size() != 0;
		for (Attribute<KPSolution> attribute : attributes)
			isTabu &= tenures.containsKey(attribute);
		return isTabu;
	}
	
}
