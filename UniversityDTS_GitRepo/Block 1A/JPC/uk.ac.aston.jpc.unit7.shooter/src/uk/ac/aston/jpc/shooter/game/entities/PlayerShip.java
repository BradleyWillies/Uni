package uk.ac.aston.jpc.shooter.game.entities;

import uk.ac.aston.jpc.shooter.game.Controls;
import uk.ac.aston.jpc.shooter.game.Game;
import uk.ac.aston.jpc.shooter.game.graphics.Sprite;
import uk.ac.aston.jpc.shooter.game.graphics.SpriteSheet;

public class PlayerShip extends AbstractGameEntity {

	public static final String REGION = "playerShip";
	private static final int MAX_FIRE_COOLDOWN = Game.FPS / 2;

	private final Controls controls;
	private int fireCooldown = 0;

	public PlayerShip(SpriteSheet sheet, Controls controls) {
		super(new Sprite(sheet.getRegion(REGION)));
		this.controls = controls;
	}

	@Override
	public void tick(Game game) {
		moveShip();
		fire(game);
	}

	private void fire(Game game) {
		if (controls.isActive(Controls.ACTION_FIRE) && fireCooldown == 0) {
			fireCooldown = MAX_FIRE_COOLDOWN;

			// We spawn the new bullet centered on our ship, above it and separated by 5px
			Bullet newBullet = new Bullet(game.getSpriteSheet());
			newBullet.setXY(
				getX() + getWidth()/2 - newBullet.getWidth()/2,
				getY() - newBullet.getHeight() - 5
			);

			game.addEntity(newBullet);
		}

		if (fireCooldown > 0) {
			--fireCooldown;
		}
	}

	private void moveShip() {
		double speedX = 0, speedY = 0;
		if (controls.isActive(Controls.ACTION_DOWN)) {
			speedY = 4;
		} else if (controls.isActive(Controls.ACTION_UP)) {
			speedY = -4;
		}

		if (controls.isActive(Controls.ACTION_LEFT)) {
			speedX = -4;
		} else if (controls.isActive(Controls.ACTION_RIGHT)) {
			speedX = 4;
		}

		setXY(getX() + speedX, getY() + speedY);
	}

}
