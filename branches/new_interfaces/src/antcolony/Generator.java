package antcolony;

import core.Generation;
import java.util.Random;


public class Generator implements core.Generator{
	
	int[][] weight;
	double[][] feromon;
	int n = 0;
	int[] result;
	int v0 = 0;
	int v = 0;
	int v_tmp = 0;
	AntColonyAlgoritm a;
	int[][] tut_bili;
	int[] path ;
	int[] zahodili; 
	
	
	public Generator(int we[][], int ver, AntColonyAlgoritm a0) {
		n = we[0].length;
		weight = new int[n][n];
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
			path[i]=0;
		}
		zahodili = new int[n]; 
	}
	
	public Solution getNextSolution(Solution s){
		feromon = s.GetFeromon();
		
		Solution ss = new Solution(result, feromon);
		return ss;
	}
	
	public Generation getNext(Generation g){	
		Solution past_s = g.getSolution();
		feromon = past_s.GetFeromon();
		Generation new_g;
		Solution n_s;
		
		for(int i=0;i<n;i++){
			zahodili[i] = 0;
		}
		
		/* случай для полносвязного графа когда нельзя заходить в вершину, в которой уже были
		 * считаем по умолчанию alfa = 0.5
		 * 	beta = 0.5
		 *  бегает 1 муравей	
		 *  испарения феромонов нет
		 *  все константы берем из класса AntColonyAlgoritm
		 */
			//int df = getNextVer();
		
		return g;
	}
	
	private int getNextVer(){
		int v_next = 0;		
		double alfa = a.getAlfa();
		double beta = a.getBeta();
		double sub_fer = a.getSubFer();		
		int f_good = 0;
		v_tmp = v0;
		double[] ver_per = new double[n];
		double sum = 0;
	
		for(int i=0;i<n;i++){
			if(weight[v_tmp][i] != 0 && zahodili[i] == 0){
				ver_per[i] = Math.pow(1/weight[v_tmp][i],alfa)*Math.pow(feromon[v_tmp][i], beta);
				sum += ver_per[i];
			}
			else{
				ver_per[i]=0;
			}
		}
		for(int i=0;i<n;i++){
		ver_per[i] = ver_per[i]/sum;
		}				
		Random rn = new Random();
		 double d =0;
		 d = rn.nextDouble();
		 
		 double s = 0;
		 int ii = 0;
		 while(s<d){
			 s+=ver_per[ii];
			 ii++;
		 }
			
		v_next = ii;		
		
		return v_next;
	}
}
