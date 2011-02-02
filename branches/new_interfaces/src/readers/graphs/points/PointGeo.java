package readers.graphs.points;

public class PointGeo extends Point2D {
	
	public PointGeo(double x, double y) {
		super(x, y);
	}
	
	public PointGeo(Point2D point) {
		super(point.getX(), point.getY());
	}
	
	private double degToRad(double coord) {
		double deg = ((int) coord) + (coord - (int) coord) * 5. / 3.;
		return Math.PI * deg / 180.;
	}
	
	@Override
	public double distanceTo(Point2D point) {
		PointGeo that = (PointGeo)point;
		
		double a0r = degToRad(this.getX());
		double b0r = degToRad(that.getX());
		double a1r = degToRad(this.getY());
		double b1r = degToRad(that.getY());
		
		double c1 = Math.cos(a1r - b1r);
		double c2 = Math.cos(a0r - b0r);
		double c3 = Math.cos(a0r + b0r);
		return (int) (1. + 6378.388 * Math
				.acos(.5 * ((1. + c1) * c2 - (1. - c1) * c3)));
	}
	
}
