package antcolony.illustration;

import java.awt.Color; 
import java.awt.Graphics; 
import java.util.Iterator; 
import java.util.Vector; 
import javax.swing.JFrame; 
import javax.swing.JPanel; 

public class Draw  extends JFrame{
	
	 private GraphPanel g;
	 private Vector<Double> d;
	 private double[][] v;

	   public Draw( double[][] dd)
	   {
	     /* d = new Vector<Double>();

	      // Заполняем вектор данными - например для функции
	     /* for (int x = 1; x <= 10; x++) {
	            d.add(1.0 / 2.0 * Math.sin(x) + 5);

	      }*
	       d.add((double) -1);
	       d.add((double) -1);
	       d.add((double) 1);
	       d.add((double) 1);
	       d.add((double) -1);
	       d.add((double) 1);
	       d.add((double) 1);
	       d.add((double) -1);
	       d.add((double) 0);
	       d.add((double) 0);*/
		 
		 v = dd;

	      g = new GraphPanel(v);
	      getContentPane().add(g);
	      setBounds(500, 500, 500, 500);
	     
	   }
	    /** Creates a new instance of TG */
	    
	    
	    /**
	     * @param args the command line arguments
	     */
	  /*  public static void main(String[] args) {
	       Draw t = new Draw();
	      t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      t.setVisible(true);
	    }*/
	    
	    public void drawField( double[][] ddd){
	    	 Draw t = new Draw(ddd);
		      t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      t.setVisible(true);	    	
	    }

}
