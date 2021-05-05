package uk.ac.aston.jpc.shooter.game;

import javafx.scene.shape.Rectangle;

/**
 * A game entity is both visible on the screen, and has behaviour of its own.
 */
public interface GameEntity extends Actor, Drawable {

	/**
	 * Returns the collision box for this entity.
	 */
	Rectangle getCollisionBox();

	/**
	 * Returns {@code true} if this entity collides with the {@code other} one.
	 */
	default boolean collidesWith(GameEntity other) {
		final Rectangle otherBox = other.getCollisionBox();
		final Rectangle myBox = getCollisionBox();
		return otherBox.intersects(myBox.getBoundsInParent());
	}

}
