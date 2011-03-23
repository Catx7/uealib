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
    private Vector<Double> data;

    public GraphPanel(Vector<Double> data) {
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
      
        for (Iterator<Double> it = data.iterator(); it.hasNext();) {
        	double x = it.next().doubleValue();
            double y = it.next().doubleValue();
            
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
