package uk.ac.aston.jpd.lab3.acint.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents an object that can be drawn on the screen.
 */
public interface Drawable {

	/**
	 * Draws the current object to the screen.
	 * 
	 * @param gc JavaFX {@link GraphicsContext} to be used to draw on the screen.
	 */
	void draw(GraphicsContext gc);
}
