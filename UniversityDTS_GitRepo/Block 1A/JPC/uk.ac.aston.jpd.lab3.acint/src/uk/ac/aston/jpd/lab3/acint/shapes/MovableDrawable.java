package uk.ac.aston.jpd.lab3.acint.shapes;

public interface MovableDrawable extends Drawable {

	MovableDrawable move(int dx, int dy);
	
	default MovableDrawable moveXY(int d) {
		return move(d, d);
	}
	
	default MovableDrawable moveX(int d) {
		return move(d, 0);
	}
	
	default MovableDrawable moveY(int d) {
		return move(0, d);
	}
}
