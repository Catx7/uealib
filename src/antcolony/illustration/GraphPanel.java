package antcolony.illustration;

import java.awt.BasicStroke;
import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.util.Iterator; 
import java.util.Vector; 
import javax.swing.JFrame; 
import javax.swing.JPanel;

class GraphPanel extends JPanel {
    private double[][] data;

    public GraphPanel(double[][] data) {
        this.data = data;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // максимальная ширина графика - 10
        // максимальная высота = 1, минимальная = -1
        // Вычисляем коэффициенты

        double Kx = getWidth() / 2;
        double Ky = getHeight() / 2;
        double k = 180;
       // double Kx = getWidth();
       // double Ky = getHeight();

        double xo = -1;
        double yo = -1;

        	g.drawLine((int)Kx, 0, (int)Kx, getHeight());
        	g.drawLine(0, (int)Ky, getWidth(), (int)Ky);
      
        for (int i = 0; i<data.length/data[0].length;i++) {
        	double x = data[i][0];
            double y = data[i][1];
            
            double xx = x*k;
             double yy = y*k;

            // Рисование начинаем для второй точки
           
             Graphics2D g2 = (Graphics2D) g;
               
               // g.drawLine(x1, y1, x2, y2);
              g2.setStroke(new BasicStroke(3.0f));
              g2.setColor(new Color(456789));
                 g2.drawOval((int)(xx+Kx), (int)(yy+Ky), 3, 3);
                           

        }
    }
}
