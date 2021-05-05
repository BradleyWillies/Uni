package uk.ac.aston.jpc.shooter.game.entities;

import javafx.scene.shape.Rectangle;
import uk.ac.aston.jpc.shooter.game.Game;
import uk.ac.aston.jpc.shooter.game.graphics.Sprite;
import uk.ac.aston.jpc.shooter.game.graphics.SpriteSheet;

/**
 * Represents an enemy ship. Right now they just stand there, doing nothing.
 */
public class EnemyShip extends AbstractGameEntity {

	public static final String REGION = "enemyShip";

	public EnemyShip(SpriteSheet sheet) {
		super(new Sprite(sheet.getRegion(REGION)));
	}

	@Override
	public Rectangle getCollisionBox() {
		return new Rectangle(getX(), getY() - 6, getWidth(), getHeight() - 12);
	}

	@Override
	public void tick(Game game) {
		// TODO nothing to do - add your own logic here!
	}

}
