package uk.ac.aston.jpd.lab2.drawing.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Circle, specified as center + radius. 
 */
public class Circle {

	private double centerX, centerY;
	private double radius;

	public Circle(double centerX, double centerY, double radius) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
	}

	public double getX() { return centerX; }
	public double getY() { return centerY; }
	public double getWidth()  { return radius * 2; }
	public double getHeight() { return radius * 2; }

	public void draw(GraphicsContext gc) {
		gc.strokeOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
	}

	public double getRadius() { return radius; }
	
}
