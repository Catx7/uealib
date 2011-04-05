package simulatedannealing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import simulatedannealing.ChartTracer.Type;

public class ChartTracer {
	
	public enum Type 
	{
		IterationToFitness,
		DimensionToPerformance,
	}
	
	private int skipPoint;
	private int currentSkip;
	private long timeStart;
	private long timeEnd;
	private Type type;
	
	public ChartTracer(Type type) {
		header = new HashMap<String, String>();
		pointPull = new TreeMap<Integer, Double>();
		this.type = type;
		
		setTraceCount(10);
	}
	
	
	private HashMap<String, String> header;
	private SortedMap<Integer, Double> pointPull;
	
	public void timeStart() {
		timeStart = System.currentTimeMillis();
	}
	
	public void timeEnd() {
		timeEnd = System.currentTimeMillis();
		header.put("TimePerformance", Long.toString(timeEnd - timeStart));
	}
		
	public void Trace(int x, double y) {
		if(currentSkip == skipPoint) {
			AddPoint(x, y);
			currentSkip = 1;
		}
		else
		{
			currentSkip++;
		}
		
	}
	
	public void setTraceCount(int count) {
		skipPoint = count;
		currentSkip = skipPoint;
	}
	
	public void AddPoint(int x, double y) {
		pointPull.put(x, y);
	}
	
	public void AddHeader(String key, String value) {
		
		header.put(key, value);
	}
	
	public void serializeToFile(File dir) throws Exception {
		if(!header.containsKey("TaskName")) {
			throw new Exception("Not set TaskName header");
		}
		
		if(!header.containsKey("TaskDimension")) {
			throw new Exception("Not set TaskDimension header");
		}
		
		String fileName = header.get("TaskName")+"_"+header.get("TaskDimension");
		if(type == Type.DimensionToPerformance)
			fileName += "_performance";
		else
			fileName += "_quality";
		
		fileName = dir.getAbsolutePath()+"/" + fileName + ".txt";
		File target = new File(fileName);
		if(!target.exists())
		{
			try
			{
				target.createNewFile();
			}
			catch(IOException e)
			{
				System.out.println(dir.getAbsolutePath());
			}
		}
		
		PrintWriter out = new PrintWriter(
					new FileOutputStream(target)
					);
		
		for (Map.Entry<String, String> entry : header.entrySet()) {
			out.println("# "+entry.getKey()+":"+entry.getValue());
		}
		
		out.println();
		
		for (Map.Entry<Integer, Double> entry : pointPull.entrySet()) {
			out.println(entry.getKey()+" "+entry.getValue());
		}
		out.flush();
		out.close();
	}
	
	public Type getType() {
		return type;
	}
	
}
