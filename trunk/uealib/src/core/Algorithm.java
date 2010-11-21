package core;

public class Algorithm {

        private Initializator init;
        private Generator generator;
        private StoppingCriteria stoppingCriteria;
        private Selector selector;
        private TransitionCriteria transitionCriteria;
        
        public Algorithm(Initializator i,
        				 Generator g,
        				 StoppingCriteria stop,
        				 Selector selector,
        				 TransitionCriteria trans) {
        	
                this.init = i;
                this.generator = g;
                this.stoppingCriteria = stop;
                this.selector = selector;
                this.transitionCriteria = trans;
        }
        
        public Generation solve() {
        	Generation currentGeneration = this.init.getInitialGeneration();
        	
        	while ( !stoppingCriteria.isSatisfied(currentGeneration) ) {
 
                Generation g = generator.getNext(currentGeneration);
                Generation h = selector.keepTheBestSolutions(g);
 
                if ( transitionCriteria.isSatisfied(currentGeneration, h) ) {
                	currentGeneration = h;
                }
        	}
        	
        	return currentGeneration;
        }
}
