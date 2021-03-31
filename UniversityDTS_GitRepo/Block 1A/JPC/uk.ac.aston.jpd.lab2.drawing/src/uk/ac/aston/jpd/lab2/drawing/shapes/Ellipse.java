package uk.ac.aston.jpd.lab2.drawing.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Ellipse, specified as upper left corner + width and height. 
 */
public class Ellipse {

	private double upperLeftX, upperLeftY;
	private double width, height;

	public Ellipse(double upperLeftX, double upperLeftY, double width, double height) {
		this.upperLeftX = upperLeftX;
		this.upperLeftY = upperLeftY;
		this.width = width;
		this.height = height;
	}

	public double getX() { return upperLeftX; }
	public double getY() { return upperLeftY; }
	public double getWidth()  { return width; }
	public double getHeight() { return height; }

	public void draw(GraphicsContext gc) {
		gc.strokeOval(upperLeftX, upperLeftY, width, height);
	}
	
}
