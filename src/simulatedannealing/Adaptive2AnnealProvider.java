package simulatedannealing;

public class Adaptive2AnnealProvider implements IAnnealProvider {
	private SimulatedAnnealingContext ctx;
	private double lambda;
	
	public Adaptive2AnnealProvider(SimulatedAnnealingContext ctx, double lambda){
		this.ctx = ctx;
		this.lambda = lambda;
	}
	@Override
	public double anneal(double t) {
		double variance = 0;
		double mean = 0;
		for(Double cur: ctx.statistic) {
			mean += cur/ctx.statistic.size();
		}
		
		for(Double cur: ctx.statistic) {
			variance += Math.pow(cur-mean, 2);
		}
		
		double standartDeviation = Math.sqrt(variance/(ctx.statistic.size()-1));
		
		double result = t/(Math.exp((lambda*t)/standartDeviation));
		if(result > t)
			return t;
		return result;
	}

}
