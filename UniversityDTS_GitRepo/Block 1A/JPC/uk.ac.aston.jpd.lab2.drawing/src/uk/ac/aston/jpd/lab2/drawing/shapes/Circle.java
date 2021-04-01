package uk.ac.aston.jpd.lab2.drawing.shapes;

/**
 * Circle, specified as center + radius. 
 */
public class Circle extends Ellipse {

	public Circle(double centerX, double centerY, double radius) {
		super(centerX - radius, centerY - radius, radius * 2, radius * 2);
	}

	public double getRadius() { 
		return getWidth() / 2; 
	}
	
	public Shape move(double dx, double dy) {
		return new Circle(getUlX() + dx, getUlY() + dy, getWidth());
	}
}
