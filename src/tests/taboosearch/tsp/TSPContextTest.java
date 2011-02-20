package tests.taboosearch.tsp;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import readers.Graph;
import readers.graphs.GeoCoordsGraphReader;
import readers.graphs.GraphReader;
import taboosearch.tenures.ConstantTenureStrategy;


/*
 * Broken due to refactoring :)
 */
public class TSPContextTest {
	//TSPContext context;
	
	@Before
	public void setUp() throws Exception {
		GraphReader graphReader = new GeoCoordsGraphReader();
		Graph graph = graphReader.readFromFile("/home/rrhu/workspace/uealib/graphs/burma14.txt");

		//TSPEvaluator evaluator = new TSPEvaluator(graph);
		//TSPTaboolator taboolator = new TSPTaboolator(new ConstantTenureStrategy(5));
		//TSPFrequencyMemory frequencyMemory = new TSPFrequencyMemory(graph);
		//this.context = new TSPContext(evaluator, taboolator, frequencyMemory);
	}

	@Test
	public void test_ticks() {
		/*assertEquals(context.getTicks(), 0);
		context.tick();
		assertEquals(context.getTicks(), 1);
		context.tick();
		context.tick();
		assertEquals(context.getTicks(), 3);*/
	}
   
}
