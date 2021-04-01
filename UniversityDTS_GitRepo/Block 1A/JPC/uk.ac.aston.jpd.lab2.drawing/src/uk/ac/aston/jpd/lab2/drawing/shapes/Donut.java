package uk.ac.aston.jpd.lab2.drawing.shapes;

import javafx.scene.canvas.GraphicsContext;

public class Donut extends Circle {

	public Donut(double centerX, double centerY, double radius) {
		super(centerX, centerY, radius);
	}
		
	@Override
	public void draw(GraphicsContext gc) {
		super.draw(gc);
		gc.strokeOval(getUlX() + getRadius() / 2, getUlY() + getRadius() / 2, getRadius(), getRadius());
	}
	
	@Override
	public Shape move(double dx, double dy) {
		return new Donut(getUlX() + dx, getUlY() + dy, getWidth() / 2);
	}
}
