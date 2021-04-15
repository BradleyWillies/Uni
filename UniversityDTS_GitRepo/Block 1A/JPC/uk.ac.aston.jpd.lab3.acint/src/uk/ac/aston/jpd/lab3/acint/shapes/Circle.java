package uk.ac.aston.jpd.lab3.acint.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Circle, specified as center + radius. 
 */
public class Circle implements MovableDrawable {
	
	private final double centerX, centerY, radius;

	public Circle(double centerX, double centerY, double radius) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
	}
	
	public double getCenterX() {
		return centerX;
	}
	
	public double getCenterY() {
		return centerY;
	}

    public double getRadius() { 
    	return radius; 
    }

    public void draw(GraphicsContext gc) {
    	gc.strokeOval(getCenterX() - getRadius(), getCenterY() - getRadius(), getRadius() * 2, getRadius() * 2);
    }
    
    @Override
    public Circle move(int dx, int dy) {
    	return new Circle(getCenterX() + dx, getCenterY() + dy, getRadius());
    }
}
