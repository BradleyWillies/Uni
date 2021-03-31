package uk.ac.aston.jpd.lab2.drawing.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Rectangle, specified as upper left corner + width and height. 
 */
public class Rectangle extends Shape {

	public Rectangle(double ulX, double ulY, double width, double height) {
		super(ulX, ulY, width, height);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeRect(getUlX(), getUlY(), getWidth(), getHeight());
	}

}
