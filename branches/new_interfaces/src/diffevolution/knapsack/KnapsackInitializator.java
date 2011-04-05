package diffevolution.knapsack;
import readers.KnapsackTask;
import java.util.LinkedList;
import readers.items.Item;
import diffevolution.knapsack.KnapsackSolution;
import diffevolution.Initializator;

public class KnapsackInitializator extends Initializator<KnapsackSolution, KnapsackGeneration> {
	private Item[] items;
	private int n = 0;
	private double V;
	private static java.util.Random rand = new java.util.Random();
	
	public static final int DEFAULT_GENERATION_SIZE = 100;
	private int generationSize;

	public KnapsackInitializator(KnapsackTask c) {	
		this.items = c.getItems();
		this.n = c.getItemsNumber();
		this.V = c.getCapacity();
		this.generationSize = DEFAULT_GENERATION_SIZE;
		int[] limits = new int[this.n]; 
		for (int i = 0; i < this.n; ++i) {
			limits[i] = (int) (this.V / this.items[i].getWeight()) + 1;
		}
		KnapsackSolution.maxItemNum = limits;
	}
	
	
	private KnapsackSolution getSolution() {
		LinkedList<Integer> itemSet = new LinkedList<Integer>();
		for (int i = 0; i < this.n; ++i) 
			itemSet.add(rand.nextInt(KnapsackSolution.maxItemNum[i] + 1));
		return new KnapsackSolution(itemSet);
	}
	
	private KnapsackSolution getGreedySolution() {
		int[] itemSet = new int[this.n];
		LinkedList<Integer> index = new LinkedList<Integer>();
		
		for (int i = 0; i < this.n; ++i)
			itemSet[i] = 0;
		
		double sumWeights = 0;
		for (int i = 0; i < this.n; ++i) {
			int max = 0;	
			for (int j = 0; j < this.n; ++j)  
				if (!index.contains(j) && this.items[j].getUtility() > this.items[max].getUtility())
					max = j;
			while (sumWeights + this.items[max].getWeight() < this.V && itemSet[max] < KnapsackSolution.maxItemNum[max] ) {
				++itemSet[max];
				sumWeights += this.items[max].getWeight();
			}
			index.add(max);
		}
		return new KnapsackSolution(itemSet);
	}
	
	public KnapsackGeneration getInitialGeneration() {		
		KnapsackGeneration result = new KnapsackGeneration();
		
		result.add(getGreedySolution());
		int inx = 1;
		while ( inx < this.generationSize ) {
	  		result.add(getSolution());
	    	++inx;
		}
		return result;
	}

	public void setGenerationSize(int size) {
		this.generationSize = size;		
	}
	
	public void setVariaty01KnapsackProblem() {
		for (int i = 0; i < this.n; ++i) 
			KnapsackSolution.maxItemNum[i] = 1;
	}
	
	public void setVariatyKnapsackProblem(int[] maxNum) {
		for (int i = 0; i < this.n; ++i) 
			KnapsackSolution.maxItemNum[i] = maxNum[i];
	}
}
	
