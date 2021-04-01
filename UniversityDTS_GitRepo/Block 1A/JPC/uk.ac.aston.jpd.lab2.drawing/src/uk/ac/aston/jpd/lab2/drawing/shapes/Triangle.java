package uk.ac.aston.jpd.lab2.drawing.shapes;

import javafx.scene.canvas.GraphicsContext;

public class Triangle extends Shape {
	
	public Triangle(double ulX, double ulY, double width, double height) {
		super(ulX, ulY, width, height);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.lineTo(getUlX(), getUlY());
		gc.lineTo(getUlX(), getUlY() + getHeight());
		gc.lineTo(getUlX() + getWidth(), getUlY() + getHeight());
		gc.lineTo(getUlX(), getUlY());
		gc.stroke();
	}
	
	@Override
	public Shape move(double dx, double dy) {
		return new Triangle(getUlX() + dx, getUlY() + dy, getWidth(), getHeight());
	}
}
