package uk.ac.aston.jpd.lab3.acint.shapes;

import java.awt.Polygon;
import java.awt.geom.PathIterator;

import javafx.scene.canvas.GraphicsContext;

public class MyPolygon extends Polygon implements Drawable {
	
	@Override
	public void draw(GraphicsContext gc) {
		final double[] coords = new double[6];
		
		for (PathIterator itPath = this.getPathIterator(null) ; !itPath.isDone() ; itPath.next()) {
			final int segmentType = itPath.currentSegment(coords);
			if (segmentType == PathIterator.SEG_MOVETO) {
				gc.beginPath();
				gc.moveTo(coords[0], coords[1]);
			}
			if (segmentType == PathIterator.SEG_LINETO) {
				gc.lineTo(coords[0], coords[1]);
			}
			if (segmentType == PathIterator.SEG_CLOSE) {
				gc.closePath();
				gc.stroke();
			}
		}
	}
}
