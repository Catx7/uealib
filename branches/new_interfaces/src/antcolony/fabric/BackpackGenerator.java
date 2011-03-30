package antcolony.fabric;

import antcolony.fabric.GenerationFabric;

public class BackpackGenerator extends GeneratorFabric{

	private double capacity;
	private double sum_cup=0;
	private double[] ves;
	
	public BackpackGenerator(double we[][], int ver, AntAlgoritmParam a0) {
		n = we[0].length;
		weight = new double[n][n];
		weight = we;
		feromon = new double[n][n];
		result = new int[n];
		v0 = ver;
		a = a0;
		tut_bili = new int[n][n];
		path = new int[n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				tut_bili[i][j] = 0;
			}
			path[i]=999999999;
		}
		zahodili = new int[n]; 
	}
	@Override
	/* 
	 *  we[i][j] - ���� j-�� �������� � �������
	 */
	public GenerationFabric getNext(core.Generation g) {
		antcolony.fabric.GenerationFabric gg = (antcolony.fabric.BackpackGeneration)g;
		AntSolutionFabric past_s = gg.getSolution();
		capacity = gg.getCapacity();
		ves = gg.getWeight();
		feromon = past_s.GetFeromon();
		tmp = 0;
		sum_cup = 0;
		int v0 = rn.nextInt(n);
		//System.out.println(vproba);
		
		
		for(int i=0;i<n;i++){
			zahodili[i] = 0;
		
		}
		//System.out.println(v0);
		if(v!=123){
		sum_cup += ves[v0];}
		zahodili[v0] += 1;
		v_tmp = v0;
		v = getNextVer();
		//System.out.println(v);
		if(v!=123){
		sum_cup += ves[v];}
		if(sum_cup>=capacity){return rezGen();}
		path[tmp] = v0;
		tmp++;
		path[tmp] = v;
		v_tmp = v;
		zahodili[v] += 1;
		v = getNextVer();
		//System.out.println(v);
		if(v!=123){
		sum_cup += ves[v];}
		if(sum_cup>=capacity){return rezGen();}
		
		while(123 != v){
			tmp++;
			v_tmp = v;
			path[tmp] = v;
			zahodili[v] += 1;
			v = getNextVer();
			if(v!=123){
			sum_cup += ves[v];}
			//System.out.println(v);
			if(sum_cup>=capacity){return rezGen();}
		}
		
		return rezGen();
		
	}

	private GenerationFabric rezGen(){
		double q = 1000;
		double len_path = 0;
		double we_path = 0;
		for(int i=1;i<n;i++){
			if(path[i]!=999999999 && path[i-1]!=999999999 ){
				len_path += weight[path[i]][path[i-1]];	
				we_path += ves[i];
			}
		}
		if(path[n-1]!=999999999 && path[0]!=999999999 ){
		len_path += weight[path[n-1]][path[0]];
		}
		double koef = len_path/we_path;
		for(int i=1;i<n;i++){
			if(path[i]!=999999999 && path[i-1]!=999999999 ){
			feromon[path[i]][path[i-1]] += koef;
			feromon[path[i-1]][path[i]] += koef;
			}
		}
		if(path[0]!=999999999 && path[n-1]!=999999999 ){
		feromon[path[0]][path[n-1]] += koef;
		feromon[path[n-1]][path[0]] += koef;
		}
		AntSolutionFabric n_s;
			n_s = new BackpackSolution(zahodili, feromon, weight);
			
		antcolony.fabric.GenerationFabric new_g = new antcolony.fabric.BackpackGeneration(n_s, capacity,ves);
				
		return new_g;
	}
	
	private int getNextVer(){
		int v_next = 0;		
		double alfa = a.getAlfa();
		double beta = a.getBeta();
//		double sub_fer = a.getSubFer();		
//		int f_good = 0;
		//v_tmp = v0;
		double[] ver_per = new double[n];
		double sum = 0;
		
		int k = n;
		for(int i=0;i<n;i++){
			k -= zahodili[i];
		}
		if(k==0){return 123;}
	
		int flag = 0;
		for(int i=0;i<n;i++){
			if(weight[v_tmp][i] != 0 && sum_cup+ves[i]<=capacity){
				ver_per[i] = Math.pow(weight[v_tmp][i],alfa)*Math.pow(feromon[v_tmp][i], beta)*Math.pow(1/ves[i], beta);
				sum += ver_per[i];
				flag += 1;
			}
			else{
				ver_per[i]=0;
			}
		}
		if(flag==0){return 123;}
		for(int i=0;i<n;i++){
			ver_per[i] = ver_per[i]/sum;
			//System.out.println(i);
			//System.out.println(ver_per[i]);
		}				
		//Random rn = new Random();
		 double d =0;
		 d = rn.nextDouble();
		// System.out.println(d);
		 
		 double s = 0;
		 int ii = 0;
		 while(s<d){
			 s+=ver_per[ii];
			 ii++;
		 }
		//if(ii>n-1){ii=n-1;}	
		 if(ii!=0){
		v_next = ii-1;}		
		//System.out.println(v_next);
		return v_next;
	}
	
}