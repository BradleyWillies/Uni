package uk.ac.aston.jpc.shooter.game;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents something that can be drawn on the screen.
 */
public interface Drawable extends Positionable {

	/**
	 * Draws the object on the screen.
	 */
	void draw(GraphicsContext ctx);

}
