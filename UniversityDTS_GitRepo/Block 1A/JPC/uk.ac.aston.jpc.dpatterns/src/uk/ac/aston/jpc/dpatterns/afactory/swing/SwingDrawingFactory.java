package uk.ac.aston.jpc.dpatterns.afactory.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import uk.ac.aston.jpc.dpatterns.afactory.Circle;
import uk.ac.aston.jpc.dpatterns.afactory.Drawing;
import uk.ac.aston.jpc.dpatterns.afactory.DrawingFactory;

public class SwingDrawingFactory implements DrawingFactory {

	private final JFrame frame;
	private SwingDrawing canvas;
	
	public SwingDrawingFactory() {
		frame = new JFrame();
	}

	@Override
	public Drawing createDrawing(int w, int h) {
		if (canvas == null) {
			frame.setSize(w, h);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);

			canvas = new SwingDrawing();
			canvas.setPreferredSize(new Dimension(w, h));
			canvas.setMinimumSize(new Dimension(w, h));
			canvas.setMaximumSize(new Dimension(w, h));

			frame.setLayout(new BorderLayout());
			frame.add("Center", canvas);
			frame.pack();
			frame.setVisible(true);
		}

		return canvas;
	}

	@Override
	public Circle createCircle(int cx, int cy, int radius) {
		SwingCircle circle = new SwingCircle(cx, cy, radius);
		canvas.getCircles().add(circle);
		return circle;
	}

}
