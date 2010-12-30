package genetic.tsp;

public class SalesmanRoute extends genetic.Solution<SalesmanRoute> {

	protected int[] tour;

	protected java.util.Random rand;

	static enum implementedCrossoverMethod {
		PMX, EMX
	}

	protected static implementedCrossoverMethod crossoverMethod = implementedCrossoverMethod.PMX;

	public SalesmanRoute(int[] tour) {
		this.tour = tour;
		this.rand = new java.util.Random();
		this.occurrencesCount = new int[tour.length];
	}

	public int[] getTour() {
		return tour;
	}

	@Override
	public void doCrossover(SalesmanRoute parent2) {
		switch (crossoverMethod) {
		case EMX:
			doEMX(parent2);
			break;
		case PMX:
		default:
			doPMX(parent2);
		}
	}

	private int[] occurrencesCount;

	private void doPMX(SalesmanRoute parent2) {
		for (int i = 0; i < tour.length; ++i)
			occurrencesCount[tour[i]] = 0;
		int point1 = rand.nextInt(tour.length - 1);
		int point2 = rand.nextInt(tour.length - 1);
		if (point1 > point2) {
			int tmp = point1;
			point1 = point2;
			point2 = tmp;
		}
		for (int i = point1; i <= point2; ++i) {
			int tmp = tour[i];
			tour[i] = parent2.tour[i];
			parent2.tour[i] = tmp;
		}
		for (int i = 0; i < tour.length; ++i)
			++occurrencesCount[tour[i]];
		int j = 0;
		boolean swapped = false;
		for (int i = 0; i < point1; ++i, j = 0) {
			if (occurrencesCount[tour[i]] > 1) {
				for (swapped = false; j < point1 && !swapped; ++j)
					if (0 == occurrencesCount[parent2.tour[j]]) {
						int tmp = tour[i];
						tour[i] = parent2.tour[j];
						parent2.tour[j] = tmp;
						occurrencesCount[tour[i]] = occurrencesCount[parent2.tour[j]] = 1;
						swapped = true;
					}
				if (!swapped) {
					for (j = point2 + 1; 0 < occurrencesCount[parent2.tour[j]]; ++j)
						;
					int tmp = tour[i];
					tour[i] = parent2.tour[j];
					parent2.tour[j] = tmp;
					occurrencesCount[tour[i]] = occurrencesCount[parent2.tour[j]] = 1;
				}
			}
		}
		for (int i = point2 + 1; i < tour.length; ++i, j = 0) {
			if (occurrencesCount[tour[i]] > 1) {
				for (swapped = false; j < point1 && !swapped; ++j)
					if (0 == occurrencesCount[parent2.tour[j]]) {
						int tmp = tour[i];
						tour[i] = parent2.tour[j];
						parent2.tour[j] = tmp;
						occurrencesCount[tour[i]] = occurrencesCount[parent2.tour[j]] = 1;
						swapped = true;
					}
				if (!swapped) {
					for (j = point2 + 1; 0 < occurrencesCount[parent2.tour[j]]; ++j)
						;
					int tmp = tour[i];
					tour[i] = parent2.tour[j];
					parent2.tour[j] = tmp;
					occurrencesCount[tour[i]] = occurrencesCount[parent2.tour[j]] = 1;
				}
			}
		}
	}

	private void doEMX(SalesmanRoute parent2) {
		// TODO
	}

	@Override
	public void mutate() {
		int point1 = rand.nextInt(tour.length);
		int point2 = rand.nextInt(tour.length);
		int tmp = tour[point1];
		tour[point1] = tour[point2];
		tour[point2] = tmp;
	}

	@Override
	public SalesmanRoute copy() {
		return new SalesmanRoute(this.tour.clone()); // shallow
	}

	@Override
	public String getRepresentation() {
		String representation = "Tour[";
		for (int i = 0; i < tour.length - 1; ++i) {
			representation += tour[i] + " "; // Q&D
		}
		return representation + tour[tour.length - 1] + "]";
	}

}
