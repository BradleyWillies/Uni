package uk.ac.aston.jpd.lab2.drawing.shapes;

import javafx.scene.canvas.GraphicsContext;

public class Frame extends Shape {
	private Rectangle outerFrame;
	private Rectangle innerFrame;
	private double frameWidth;
	
	public Frame(double ulX, double ulY, double width, double height, double frameWidth) {
		super(ulX, ulY, width, height);
		this.frameWidth = frameWidth;
		outerFrame = new Rectangle(getUlX(), getUlY(), getWidth(), getHeight());
		innerFrame = new Rectangle(ulX + frameWidth, ulY + frameWidth, width - frameWidth * 2, height - frameWidth * 2);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeRect(outerFrame.getUlX(), outerFrame.getUlY(), outerFrame.getWidth(), outerFrame.getHeight());
		gc.strokeRect(innerFrame.getUlX(), innerFrame.getUlY(), innerFrame.getWidth(), innerFrame.getHeight());
	}
	
	public double getFrameWidth() {
		return frameWidth;
	}
	
	@Override
	public Shape move(double dx, double dy) {
		return new Frame(getUlX() + dx, getUlY() + dy, getWidth(), getHeight(), getFrameWidth());
	}
}
