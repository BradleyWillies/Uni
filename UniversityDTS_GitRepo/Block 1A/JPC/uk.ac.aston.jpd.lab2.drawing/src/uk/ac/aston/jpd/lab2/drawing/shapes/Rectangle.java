package uk.ac.aston.jpd.lab2.drawing.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Rectangle, specified as upper left corner + width and height. 
 */
public class Rectangle {

	private double upperLeftX, upperLeftY;
	private double width, height;

	public Rectangle(double ulX, double ulY, double width, double height) {
		this.upperLeftX = ulX;
		this.upperLeftY = ulY;
		this.width  = width;
		this.height = height;
	}

	public double getX() { return upperLeftX; }
	public double getY() { return upperLeftY; }
	public double getWidth()  { return width;  }
	public double getHeight() { return height; }

	public void draw(GraphicsContext gc) {
		gc.strokeRect(upperLeftX, upperLeftY, width, height);
	}

}
