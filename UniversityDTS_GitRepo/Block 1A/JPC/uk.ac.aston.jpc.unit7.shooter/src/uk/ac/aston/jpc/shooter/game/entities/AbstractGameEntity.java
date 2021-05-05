package uk.ac.aston.jpc.shooter.game.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import uk.ac.aston.jpc.shooter.game.GameEntity;
import uk.ac.aston.jpc.shooter.game.graphics.Sprite;

public abstract class AbstractGameEntity implements GameEntity {

	protected final Sprite sprite;

	public AbstractGameEntity(Sprite sprite) {
		this.sprite = sprite;
	}

	@Override
	public Rectangle getCollisionBox() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void draw(GraphicsContext ctx) {
		sprite.draw(ctx);
	}

	@Override
	public double getX() {
		return sprite.getX();
	}

	@Override
	public double getY() {
		return sprite.getY();
	}

	@Override
	public void setXY(double x, double y) {
		sprite.setXY(x, y);
	}

	public double getHeight() {
		return sprite.getHeight();
	}

	public double getWidth() {
		return sprite.getWidth();
	}
}
