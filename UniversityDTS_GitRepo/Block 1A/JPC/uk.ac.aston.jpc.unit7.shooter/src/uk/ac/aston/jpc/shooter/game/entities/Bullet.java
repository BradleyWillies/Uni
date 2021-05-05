package uk.ac.aston.jpc.shooter.game.entities;

import uk.ac.aston.jpc.shooter.game.Game;
import uk.ac.aston.jpc.shooter.game.graphics.Sprite;
import uk.ac.aston.jpc.shooter.game.graphics.SpriteSheet;

/**
 * Represents a bullet fired by our ship. The bullet will go at a predetermined
 * speed and either collide with an enemy ship or disappear once it leaves the
 * screen.
 */
public class Bullet extends AbstractGameEntity {

	public static final String[] REGIONS = {
		"bullet0", "bullet1", "bullet2", "bullet3", "bullet4", "bullet5", "bullet6", "bullet7"
	};

	public Bullet(SpriteSheet sheet) {
		super(new Sprite(sheet.getRegions(REGIONS)));
	}

	// TODO tweak collision logic (uses super.method)

	@Override
	public void tick(Game game) {
		// TODO implement the bullet logic
	}

}
