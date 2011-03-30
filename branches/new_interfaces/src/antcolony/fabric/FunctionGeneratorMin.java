package antcolony.fabric;

import java.util.Vector;
import antcolony.illustration.*;
import functions.*;

public class FunctionGeneratorMin extends GeneratorFabric{
	
	private double[][] vect;
	private double[] fer;
	private double[][][] messages;
	private int size;
	private Functions funct;
	private int dim;
	
	public FunctionGeneratorMin( double[][] d) {
		this.vect = d;
	}

	public antcolony.fabric.GenerationFabric getNext(core.Generation g){	
		antcolony.fabric.GenerationFabric gg = (antcolony.fabric.FunctionGeneration)g;
		FunctionSolutionMin past_s = (FunctionSolutionMin)gg.getSolution();
		fer = past_s.getFer();
		vect = past_s.getVect();
		//System.out.println(vect[0][1]);
		funct = past_s.getFunction();
		dim = funct.getDimension();		
		messages = past_s.getMessages();
		
		//if (funct instanceof Fun1){System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");}
		
		//System.out.println(vect.length);
		
		//size = vect.length/dim;
		size = vect.length;
		//вычислим координаты центра тяжести феромонных точек
		double[][] sigma_ij = new double[size][size];
		double sigma_sr = 0;
		double[][] w_ij = new double[size][size];
		double[] w_s = new double[size];
		double[] g_j = new double[dim];//координаты центра тяжести феромонных точек. 0 - x, 1 - y
		double psi = 0.1;
		double fi = 100;
		double[] di = new double[size];
		double[] ri = new double[size];
		
		for(int j=0;j<dim;j++){
			g_j[j] = 0;
		}
		for(int i=0;i<size;i++){
			w_s[i] = 0;
			
			//double x = vect.get(i*2);
			//double y = vect.get(i*2+1);
			//di[i] = Math.exp((x*x+y*y)/(2*psi*psi))/Math.sqrt(2*psi*psi*Math.PI);
			di[i] = 0.1;
			ri[i] = di[i]/fi;
		}
		
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				double sum = 0;
				for(int h=0;h<dim;h++){
					sum += (vect[i][h]-vect[j][h])*(vect[i][h]-vect[j][h]);
				}
				sigma_ij[i][j] = Math.sqrt(sum);
			//	System.out.println(sigma_ij[i][j]);
				sigma_sr += sigma_ij[i][j];				
			}
		}
		sigma_sr = sigma_sr/(size*size);
		//System.out.println(sigma_sr);
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				w_ij[i][j] = sigma_sr*fer[i]*Math.exp((-1)*sigma_ij[i][j])/2;
			}
		}
		
		for(int j=0;j<size;j++){
			for(int i=0;i<size;i++){
				w_s[j] += w_ij[i][j];
			}
			//System.out.println(w_s[j]);
		}
		
		for(int i=0;i<size;i++){
			for(int j=0; j<size;j++){
				w_ij[i][j] /= w_s[j];
			}
		}
		
		for(int j=0;j<dim;j++){
			for(int i=0;i<size;i++){
				g_j[j] += w_ij[i][j]*vect[i][j];				
			}
			//System.out.println(g_j[j]);
		}
		
		int mes_len = messages[0].length;
		double[] m_rasst = new double[dim];
		//double[] massiv = new double[2];
		
		for(int j=0;j<size;j++){		
			double rasst = ri[j]*rn.nextDouble();
			for(int k=0;k<dim;k++){
				double rasst_1 = (vect[j][k]-g_j[k])*rasst + vect[j][k];
				m_rasst[k] = rasst_1;
				vect[j][k] = rasst_1;
			}
			
			
			int f = 0;
			for(int i=0;i<mes_len;i++){
				int u = rn.nextInt(mes_len);
				//System.out.println(u);
				if(messages[j][u][0]!=10){
					f = 1;
					i = mes_len;
					//double x_m = messages[j][u*3];
					//double y_m = messages[j][u*3+1];
					double f_m = messages[j][u][dim];
					
					//massiv[0] = m_rasst[0];
					//System.out.println(massiv[0]);
					//massiv[1] = m_rasst[1];
					double fun_fj = funct.getResult(m_rasst);
					// ищем максимум
					if(fun_fj <= f_m ){						
						sendMessage(m_rasst);						
						for(int k=0;k<dim;k++){
							int ff = -1;
							if(rn.nextBoolean()==true){ff = 1;}
							rasst = ri[j]*rn.nextDouble()*ff;
							//vect[j][k] = m_rasst[k]+ rasst;
							vect[j][k] += rasst;
						}
					}
					else{
						for(int k=0;k<dim;k++){
							int fbool = -1;
							if(rn.nextBoolean()==true){fbool = 1;}
							double new_r = ri[j]*rn.nextDouble()*fbool;
							//double new_x = m_rasst[k]+new_r;
							double new_x = messages[j][u][k]+new_r;
							vect[j][k] = new_x;
						}
					}
				}
			}
			
			if(f == 0){
				sendMessage(m_rasst);
				for(int k=0;k<dim;k++){
					int ff = -1;
					if(rn.nextBoolean()==true){ff = 1;}				
					rasst = ri[j]*rn.nextDouble()*ff;
					//vect[j][k] = m_rasst[k]+rasst;
					vect[j][k] = vect[j][k]+ rasst;
				}
			}
			
			//испарение феромонов
			/*for(int i=0; i<size; i++){
				if(fer[i]>0.001){
					fer[i]-=0.0005;
				}
			}*/
			
		}
		//пока не понятно зачем нам феромоны
		FunctionSolutionMin sol = new FunctionSolutionMin(fer, vect, funct);
		FunctionGeneration rez = new FunctionGeneration(sol);
				
		return rez;
			
	}
	
	public void sendMessage(double[] m_r){
		//System.out.println("!!");
		double[] m = new double[dim+1];
		for(int i=0;i<dim;i++){
			m[i] = m_r[i];
		}
		double funct1 = funct.getResult(m_r);
		m[dim] = funct1;
		int ant = rn.nextInt(size);
		int fl = 0;
		for(int i=0;i<size;i++){
			int a_s = rn.nextInt(messages[0].length);
			if(messages[ant][a_s][0]!=10){
				fl = 1;
				messages[ant][a_s] = m;
			}
		}
		if(fl == 0){
			int a_s = rn.nextInt(messages[0].length);
			messages[ant][a_s] = m;
		}
	}
	
	/*public double getF(double x, double y){
		double rez = 0;
		double ep = 0.00000000001;
		if(x<ep && y<ep){return 0;}
		rez = Math.sin(x*x+y*y)/(x*x+y*y);	
		return rez;
	}*/
	
	private int getNextVer(){
		
		
		return 1;
	}

	



}

