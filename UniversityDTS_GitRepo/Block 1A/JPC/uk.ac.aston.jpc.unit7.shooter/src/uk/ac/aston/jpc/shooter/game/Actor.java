package uk.ac.aston.jpc.shooter.game;

/**
 * Represents an entity with its own behaviour in the game.
 */
public interface Actor {

	/**
	 * Advances the state of this actor by one tick.
	 */
	void tick(Game game);

}
