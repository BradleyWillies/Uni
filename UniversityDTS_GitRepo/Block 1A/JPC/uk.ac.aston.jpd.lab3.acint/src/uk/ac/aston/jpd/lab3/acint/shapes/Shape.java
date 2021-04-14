package uk.ac.aston.jpd.lab3.acint.shapes;

public abstract class Shape implements Drawable {

	private final double upperLeftX, upperLeftY;
	private final double width, height;

	public Shape(double ulX, double ulY, double width, double height) {
		this.upperLeftX = ulX;
		this.upperLeftY = ulY;
		this.width  = width;
		this.height = height;
	}

	public double getX() { return upperLeftX; }
	public double getY() { return upperLeftY; }
	public double getWidth()  { return width;  }
	public double getHeight() { return height; }
}
