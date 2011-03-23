package antcolony.fabric;

import java.util.Vector;
import antcolony.illustration.*;

public class FunctionGenerator extends GeneratorFabric{
	
	private Vector<Double> vect;
	private double[] fer;
	private double[][] messages;
	private int size;
	
	public FunctionGenerator( Vector<Double> d) {
		this.vect = d;
	}

	public antcolony.fabric.GenerationFabric getNext(core.Generation g){	
		antcolony.fabric.GenerationFabric gg = (antcolony.fabric.FunctionGeneration)g;
		FunctionSolution past_s = (FunctionSolution)gg.getSolution();
		fer = past_s.getFer();
		vect = past_s.getVect();
		messages = past_s.getMessages();
		
	//	System.out.println(vect);
		
		size = vect.size()/2;
		//вычислим координаты центра тяжести феромонных точек
		double[][] sigma_ij = new double[size][size];
		double sigma_sr = 0;
		double[][] w_ij = new double[size][size];
		double[] w_s = new double[size];
		double[][] g_j = new double[size][2];//координаты центра тяжести феромонных точек. 0 - x, 1 - y
		double psi = 0.1;
		double fi = 300;
		double[] di = new double[size];
		double[] ri = new double[size];
		
		for(int i=0;i<size;i++){
			w_s[i] = 0;
			g_j[i][0] = 0;
			g_j[i][1] = 0;
			double x = vect.get(i*2);
			double y = vect.get(i*2+1);
			//di[i] = Math.exp((x*x+y*y)/(2*psi*psi))/Math.sqrt(2*psi*psi*Math.PI);
			di[i] = 0.1;
			ri[i] = di[i]/fi;
		}
		
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				double x1 = vect.get(i*2);
				double y1 = vect.get(i*2+1);
				double x2 = vect.get(j*2);
				double y2 = vect.get(j*2+1);
				sigma_ij[i][j] = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));//евклидово расстояние
				sigma_sr += sigma_ij[i][j];
			}
		}
		sigma_sr /= 2;
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				w_ij[i][j] = sigma_sr*fer[i]*Math.exp((-1)*sigma_ij[i][j])/2;
			}
		}
		
		for(int j=0;j<size;j++){
			for(int i=0;i<size;i++){
				w_s[j] += w_ij[i][j];
			}
		}
		
		for(int i=0;i<size;i++){
			for(int j=0; j<size;j++){
				w_ij[i][j] /= w_s[j];
			}
		}
		
		for(int j=0;j<size;j++){
			for(int i=0;i<size;i++){
				g_j[j][0] += w_ij[i][j]*vect.get(i*2);
				g_j[j][1] += w_ij[i][j]*vect.get(i*2+1);
			}			
		}
		
		int mes_len = messages[0].length/3;
		
		for(int j=0;j<size;j++){		
			double rasst = ri[j]*rn.nextDouble();
			double rasst_x = (vect.get(j*2)- g_j[j][0])*rasst + vect.get(j*2);
			vect.set(j*2, rasst_x);
			double rasst_y = (vect.get(j*2+1)- g_j[j][1])*rasst + vect.get(j*2+1);
			vect.set(j*2+1, rasst_y);
			
			int f = 0;
			for(int i=0;i<mes_len;i++){
				int u = rn.nextInt(mes_len);
				//System.out.println(u);
				if(messages[j][u*3]<=1){
					f = 1;
					i = mes_len;
					double x_m = messages[j][u*3];
					double y_m = messages[j][u*3+1];
					double f_m = messages[j][u*3+2];
					double fun_fj = getF(rasst_x,rasst_y);
					// ищем максимум
					if(fun_fj >= f_m ){
						int ff = -1;
						if(rn.nextBoolean()==true){ff = 1;}
						sendMessage(rasst_x, rasst_y);
						rasst = ri[j]*rn.nextDouble()*ff;
						vect.set(j*2, rasst_x+rasst);
						ff = -1;
						if(rn.nextBoolean()==true){ff = 1;}
						rasst = ri[j]*rn.nextDouble()*ff;
						vect.set(j*2+1, rasst_y+rasst);
					}
					else{
						int fbool = -1;
						if(rn.nextBoolean()==true){fbool = 1;}
						double new_r = ri[j]*rn.nextDouble()*fbool;
						double new_x = x_m+new_r;
						fbool = -1;
						if(rn.nextBoolean()==true){fbool = 1;}
						new_r = ri[j]*rn.nextDouble()*fbool;
						double new_y = y_m+new_r;
						vect.set(j*2, new_x);
						vect.set(j*2+1, new_y);
					}
				}
			}
			
			if(f == 0){
				int ff = -1;
				if(rn.nextBoolean()==true){ff = 1;}
				sendMessage(rasst_x, rasst_y);
				rasst = ri[j]*rn.nextDouble()*ff;
				vect.set(j*2, rasst_x+rasst);
				ff = -1;
				if(rn.nextBoolean()==true){ff = 1;}
				rasst = ri[j]*rn.nextDouble()*ff;
				vect.set(j*2+1, rasst_y+rasst);
			}
			
			//испарение феромонов
			for(int i=0; i<size; i++){
				if(fer[i]>0.001){
					fer[i]-=0.0005;
				}
			}
			
		}
		//пока не понятно зачем нам феромоны
		FunctionSolution sol = new FunctionSolution(fer, vect);
		FunctionGeneration rez = new FunctionGeneration(sol);
				
		return rez;
			
	}
	
	public void sendMessage(double x, double y){
		double funct = getF(x,y);
		int ant = rn.nextInt(size);
		int fl = 0;
		for(int i=0;i<size;i++){
			int a_s = rn.nextInt(messages[0].length/3);
			if(messages[ant][a_s*3]!=10){
				fl = 1;
				messages[ant][a_s*3] = x;
				messages[ant][a_s*3+1] = y;
				messages[ant][a_s*3+2] = funct;
			}
		}
		if(fl == 0){
			int a_s = rn.nextInt(messages[0].length/3);
			messages[ant][a_s*3] = x;
			messages[ant][a_s*3+1] = y;
			messages[ant][a_s*3+2] = funct;
		}
	}
	
	public double getF(double x, double y){
		double rez = 0;
		double ep = 0.00000000001;
		if(x<ep && y<ep){return 0;}
		rez = Math.sin(x*x+y*y)/(x*x+y*y);	
		return rez;
	}
	
	private int getNextVer(){
		
		
		return 1;
	}

	



}

