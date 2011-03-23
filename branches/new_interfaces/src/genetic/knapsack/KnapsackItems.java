package genetic.knapsack;

import java.util.BitSet;

public class KnapsackItems extends genetic.Solution<KnapsackItems> {

	private BitSet vector;
	
	private int length;
	
	private genetic.Evaluator<KnapsackItems> evaluator;
	
	static enum implementedCrossoverMethod {
		ONE_POINT, TWO_POINT, BURST
	}

	protected static implementedCrossoverMethod crossoverMethod = implementedCrossoverMethod.ONE_POINT;

	public KnapsackItems(BitSet vector, int length, genetic.Evaluator<KnapsackItems> evaluator) {
		super();
		this.vector = vector;
		this.length = length;
		this.evaluator = evaluator;
	}

	@Override
	public KnapsackItems copy() {
		return new KnapsackItems((BitSet) vector.clone(), length, evaluator);
	}

	@Override
	public void doCrossover(KnapsackItems parent2) {
		switch (crossoverMethod) {
		case ONE_POINT:
			doOnePointCrossover(parent2);
			break;
		case TWO_POINT:
			doTwoPointCrossover(parent2);
			break;
		case BURST:
			doBurstCrossover(parent2);
			break;
		default:
			doOnePointCrossover(parent2);
		}
	}

	private void doOnePointCrossover(KnapsackItems parent2) {
		int point = rand.nextInt(length);
		BitSet tmp = (BitSet) parent2.vector.clone();
		BitSet mask = (BitSet) vector.clone();
		mask.clear(0, point);
		parent2.vector.clear(point, length);
		parent2.vector.or(mask);
		tmp.clear(0, point);
		vector.clear(point, length);
		vector.or(tmp);
		evaluator.makeFeasibleSolution(this);
		evaluator.makeFeasibleSolution(parent2);
	}

	private void doTwoPointCrossover(KnapsackItems parent2) {
		// TODO

	}

	private void doBurstCrossover(KnapsackItems parent2) {
		// TODO

	}

	@Override
	public String getRepresentation() {
		// TODO
		return vector.toString();
	}

	@Override
	public void mutate() {
		vector.flip(rand.nextInt(length));
		evaluator.makeFeasibleSolution(this);
	}
	
	public int dropRandomItem() {
		int index;
		do {
			index = rand.nextInt(length);
		} while (!vector.get(index));
		vector.clear(index);
		return index;
	}
	
	public int getLength() {
		return length;
	}
	
	public boolean isInKnapsack(int index) {
		return vector.get(index);
	}

}
