package uk.ac.aston.jpc.dpatterns.afactory;

import javafx.application.Application;
import javafx.stage.Stage;
import uk.ac.aston.jpc.dpatterns.afactory.javafx.JFXDrawingFactory;
import uk.ac.aston.jpc.dpatterns.afactory.swing.SwingDrawingFactory;

public class AFactoryMain extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		DrawingFactory factory;
		
		if (getParameters().getRaw().contains("swing")) {
			factory = new SwingDrawingFactory();
		} else {
			factory = new JFXDrawingFactory(stage);
		}

		Drawing drawing = factory.createDrawing(300, 300);
		Circle c1 = factory.createCircle(150, 100, 50);
		Circle c2 = factory.createCircle(100, 150, 50);
		Circle c3 = factory.createCircle(200, 150, 50);

		drawing.setFill(255, 255, 255);
		c1.setFill(255, 0, 0);
		c2.setFill(0, 255, 0);
		c3.setFill(0, 0, 255);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
