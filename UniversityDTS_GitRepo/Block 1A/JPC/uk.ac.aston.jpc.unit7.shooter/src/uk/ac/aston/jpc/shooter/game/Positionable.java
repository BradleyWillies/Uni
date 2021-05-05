package uk.ac.aston.jpc.shooter.game;

/**
 * Represents something that can be positioned on the screen.
 */
public interface Positionable {
	double getX();
	double getY();
	void setXY(double x, double y);
}
