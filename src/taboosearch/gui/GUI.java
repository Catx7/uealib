package taboosearch.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GUI implements Runnable {
	
	private XYSeries series;
	
	public GUI(XYSeries series) {
		this.series = series;
	}
	
	public void run() {		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		JFreeChart chart = ChartFactory.createXYAreaChart("Test", "X", "Y", 
				dataset, PlotOrientation.VERTICAL, true, true, false);
		
		JFrame window = new ChartFrame("Result", chart);
		
		window.setMinimumSize(new Dimension(800, 400));
		window.setVisible(true);
	}
}
