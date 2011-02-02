package readers;

public class Point2D {
	private double x;
	private double y;
	
	Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getX() {
		return x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getY() {
		return y;
	}
	
	public double distanceTo(Point2D that) {
		return Math.sqrt( Math.pow(this.getX() - that.getX(), 2) +
						  Math.pow(this.getY() - that.getY(), 2) );
	}
}
