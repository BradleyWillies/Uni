package uk.ac.aston.jpc.shooter.game.entities;

import java.util.Set;

import uk.ac.aston.jpc.shooter.game.Game;
import uk.ac.aston.jpc.shooter.game.GameEntity;
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

	@Override
	public boolean collidesWith(GameEntity other) {
		if (other instanceof EnemyShip) {
			return super.collidesWith(other);
		}
		else {
			return false;
		}
	}

	@Override
	public void tick(Game game) {
		if (this.getY() < 0) {
			game.removeEntity(this);
		}
		else {
			this.setXY(getX(), getY() - 8);
			Set<GameEntity> collision = game.collidingWith(this);
			if (!collision.isEmpty()) {
				for (GameEntity entity : collision) {
					game.removeEntity(entity);
				}
				game.removeEntity(this);
				return;
			}
		}
	}

}
