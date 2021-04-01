package uk.ac.aston.jpd.lab2.drawing;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import uk.ac.aston.jpd.lab2.drawing.shapes.Circle;
import uk.ac.aston.jpd.lab2.drawing.shapes.Donut;
import uk.ac.aston.jpd.lab2.drawing.shapes.Ellipse;
import uk.ac.aston.jpd.lab2.drawing.shapes.FilledRectangle;
import uk.ac.aston.jpd.lab2.drawing.shapes.Frame;
import uk.ac.aston.jpd.lab2.drawing.shapes.Rectangle;
import uk.ac.aston.jpd.lab2.drawing.shapes.Shape;
import uk.ac.aston.jpd.lab2.drawing.shapes.Triangle;

/**
 * Main class for the Shapes lab.
 */
public class Launcher extends Application {

	private Canvas canvas;
	private GraphicsContext graphics;
	private Random rnd = new Random();
	private Shape[] shapes;

	/**
	 * Starts the application, which has only an empty canvas where we can draw
	 * once per second. Don't worry too much about the details: we will explain
	 * JavaFX in more detail in a later lecture.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox();
		canvas = new Canvas(500, 500);
		root.getChildren().add(canvas);
		graphics = canvas.getGraphicsContext2D();

		stage.setResizable(false);
		stage.setScene(new Scene(root));
		stage.show();

		shapes = new Shape[] {
			createRandomShape(), createRandomShape(), createRandomShape() 	
		};
		
		// Asks drawScene() to be run 30 times per second, Processing-style
		draw();
		Timeline drawEverySecond = new Timeline(
			new KeyFrame(Duration.millis(1000/30), (event) -> {
				draw();
			})
		);
		drawEverySecond.setCycleCount(Timeline.INDEFINITE);
		drawEverySecond.play();
	}

	private int iFrame = 0;

	private void draw() {
		graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

//		Rectangle r = new FilledRectangle(Color.AQUA, 10 * iFrame, 10 * iFrame, 50, 50);
//		r.draw(graphics);
//
//		Circle c = new Circle(200, 300 - iFrame*5, 100);
//		c.draw(graphics);
//
//		Ellipse e = new Ellipse(50, 300, 90 + iFrame, 160);
//		e.draw(graphics);
//		
//		Triangle t = new Triangle(350, 50, 100, 100);
//		t.draw(graphics);
//		
//		Donut d = new Donut(300, 300, 100);
//		d.draw(graphics);
//		
//		Frame f = new Frame(10, 10, 480, 480, 20);
//		f.draw(graphics);
		
//		for (Shape shape : shapes) {
//			shape.draw(graphics);
//		}
		
		for (int iShape = 0 ; iShape < shapes.length ; iShape++) {
			shapes[iShape] = shapes[iShape].move(
				rnd.nextFloat() * 10 - 5, 
				rnd.nextFloat() * 10 - 5
			);
			shapes[iShape].draw(graphics);
		}

		iFrame = (iFrame + 1) % 30; 
	}
	
	private Shape createRandomShape() {
		final int option = rnd.nextInt(4);
		
		final int rndX = rnd.nextInt((int) canvas.getWidth());
		final int rndY = rnd.nextInt((int) canvas.getHeight());
		final int rndW = rnd.nextInt(200);
		final int rndH = rnd.nextInt(200);
		
		if (option == 0) {
			return new Rectangle(rndX, rndY, rndW, rndH);
		} else if (option == 1) {
			return new Ellipse(rndX, rndY, rndW, rndH);
		} else if (option == 2) {
			return new Triangle(rndX, rndY, rndW, rndH);
		} else if (option == 3) {
			return new Frame(rndX, rndY, rndW, rndH, 20);
		} else {
			return null;
		}
	}
	
	private Shape scale(Shape shape, double dw, double dh) {
		if (shape instanceof Circle) {
			Circle c = (Circle) shape;
			return new Circle(shape.getUlX(), shape.getUlY(), c.getRadius() + dw);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
