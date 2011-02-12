package taboosearch.readers;

import java.util.ArrayList;

public class FeaturesSpace {
	public ArrayList<Feature> features;
	public double[][] d;
	public Feature[][] lambdas;
	public int n;
	
	public FeaturesSpace(ArrayList<Feature> features) {
		this.features = features; //new ArrayList<Feature>();
	}
	
	/*public void add(Feature feature) {
		features.add(feature);
	}*/
	
	public void computeD() {
		this.n = features.size();
		this.d = new double[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = i; j < n; ++j) {
				d[i][j] = d[j][i] = features.get(i).distanceTo(features.get(j));
			}
		}
		
	}
	
	public void computeLambdas() {
		this.lambdas = new Feature[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				lambdas[i][j] = features.get(i).sub(features.get(j)).mul(1.0 / d[i][j]);
			}
		}
		
	}
	
}
