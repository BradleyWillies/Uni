package uk.ac.aston.jpc.shooter.game.graphics;

import javafx.scene.canvas.GraphicsContext;
import uk.ac.aston.jpc.shooter.game.Drawable;
import uk.ac.aston.jpc.shooter.game.graphics.SpriteSheet.Region;

/**
 * Represents a sprite on the screen, drawn as a sequence of animation frames
 * that repeats cyclically. Allows for sub-pixel positioning.
 */
public class Sprite implements Drawable {

	private final Region[] regions;
	private double x, y;
	private int iFrame = 0;
	
	public Sprite(Region region) {
		this.regions = new Region[] { region };
	}

	public Sprite(Region[] regions) {
		this.regions = regions;
	}

	@Override
	public void draw(GraphicsContext ctx) {
		regions[iFrame].draw(ctx, x, y);
		iFrame = (iFrame + 1) % regions.length;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public int getWidth() {
		return regions[iFrame].getWidth();
	}

	public int getHeight() {
		return regions[iFrame].getHeight();
	}
}
