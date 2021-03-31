package uk.ac.aston.jpd.lab2.drawing.shapes;

import javafx.scene.canvas.GraphicsContext;

public class Shape {
	private final double ulX, ulY;
	private final double width, height;
	
	public Shape(double ulX, double ulY, double width, double height) {
		this.ulX = ulX;
		this.ulY = ulY;
		this.width = width;
		this.height = height;
	}
	
	public double getUlX() {
		return ulX;
	}

	public double getUlY() {
		return ulY;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
	
	public void draw(GraphicsContext gc) {
		System.out.println("You forgot to write the draw method in " + getClass().getName());
	}
}
