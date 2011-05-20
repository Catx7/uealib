package simulatedannealing;

public class AdaptiveAnnealProvider implements IAnnealProvider {
	private SimulatedAnnealingContext ctx;
	private double delta;
	
	public AdaptiveAnnealProvider(SimulatedAnnealingContext ctx, double delta){
		this.ctx = ctx;
		this.delta = delta;
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
		
		double result = 3*standartDeviation;
		result = (Math.log(1 + delta)*t)/result;
		result = t/(1+result);
		
		
		if(result > t)
			return t;
		return result;
	}

}
