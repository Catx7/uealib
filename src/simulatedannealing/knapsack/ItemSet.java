package simulatedannealing.knapsack;

import java.util.HashSet;

import simulatedannealing.AbstractSolution;

import core.Solution;

public class ItemSet extends AbstractSolution {
	private HashSet<Integer> used;
	private HashSet<Integer> unused;
	private int N;

	public ItemSet(HashSet<Integer> used, int N) {
		unused = new HashSet<Integer>();

		for (int i = 0; i < N; ++i)
			unused.add(i);
		this.used = used;
		for (Integer itemID : used) {
			unused.remove(itemID);
		}
		this.N = N;

	}

	public int getN() {
		return N;
	}

	public HashSet<Integer> getUsed() {
		return used;
	}

	public HashSet<Integer> getUnused() {
		return unused;
	}

	@Override
	public Solution copy() {
		// TODO Auto-generated method stub
		return null;
	}
}
