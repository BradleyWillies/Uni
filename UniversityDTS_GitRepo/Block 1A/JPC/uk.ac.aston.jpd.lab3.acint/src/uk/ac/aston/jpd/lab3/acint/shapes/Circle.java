package uk.ac.aston.jpd.lab3.acint.shapes;

/**
 * Circle, specified as center + radius. 
 */
public class Circle extends Ellipse {

	public Circle(double centerX, double centerY, double radius) {
		super(centerX - radius, centerY - radius, radius * 2, radius * 2);
	}

    public double getRadius() { return getWidth()/2; }

}
