package uk.ac.aston.jpd.lab3.acint.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Ellipse, specified as upper left corner + width and height. 
 */
public class Ellipse extends Shape {

	public Ellipse(double upperLeftX, double upperLeftY, double width, double height) {
		super(upperLeftX, upperLeftY, width, height);
	}

    @Override
	public void draw(GraphicsContext gc) {
		gc.strokeOval(getX(), getY(), getWidth(), getHeight());
	}

}
