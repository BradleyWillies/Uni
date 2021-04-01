package uk.ac.aston.jpd.lab2.drawing.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FilledRectangle extends Rectangle {
	private Color fill;
	
	public FilledRectangle(Color fill, double ulX, double ulY, double width, double height) {
		super(ulX, ulY, width, height);
		this.fill = fill;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(fill);
		gc.fillRect(getUlX(), getUlY(), getWidth(), getHeight());
		super.draw(gc);
	}
	
	public Color getFill() {
		return fill;
	}
	
	@Override
	public Shape move(double dx, double dy) {
		return new FilledRectangle(getFill(), getUlX() + dx, getUlY() + dy, getWidth(), getHeight());
	}
}
