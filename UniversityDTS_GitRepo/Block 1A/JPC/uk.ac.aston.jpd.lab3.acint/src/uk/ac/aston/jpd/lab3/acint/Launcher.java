package uk.ac.aston.jpd.lab3.acint;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import uk.ac.aston.jpd.lab3.acint.shapes.Circle;
import uk.ac.aston.jpd.lab3.acint.shapes.Drawable;
import uk.ac.aston.jpd.lab3.acint.shapes.Ellipse;
import uk.ac.aston.jpd.lab3.acint.shapes.MovableDrawable;
import uk.ac.aston.jpd.lab3.acint.shapes.MyPolygon;
import uk.ac.aston.jpd.lab3.acint.shapes.Rectangle;

/**
 * Main class for the Shapes lab.
 */
public class Launcher extends Application {

	private static final int FPS = 30;
	private static final int MAX_SPEED = 20;

	private Canvas canvas;
	private GraphicsContext graphics;

	private int iFrame = 0;
	private Drawable[] drawables;

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

		MyPolygon triangle = new MyPolygon();
		triangle.addPoint(400, 300);
		triangle.addPoint(300, 200);
		triangle.addPoint(400, 400);
		
		drawables = new Drawable[] {
			new Circle(300, 200, 100),
			new Ellipse(100, 300, 150, 200),
			new Rectangle(40, 40, 300, 100),
			triangle
		};

		// Asks drawScene() to be run 30 times per second, Processing-style
		draw();
		Timeline drawEverySecond = new Timeline(
			new KeyFrame(Duration.millis(1000/FPS), (event) -> {
				draw();
			})
		);
		drawEverySecond.setCycleCount(Timeline.INDEFINITE);
		drawEverySecond.play();
	}

	private void draw() {
		graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		for (int i = 0; i < drawables.length; i++) {
			drawables[i].draw(graphics);
			if (drawables[i] instanceof MovableDrawable) {
				MovableDrawable m = (MovableDrawable) drawables[i];
				final double d = MAX_SPEED * 2 * ((double)iFrame/FPS - 0.5);
				drawables[i] = m.move((int) d, (int) d);
			}
		}

		iFrame = (iFrame + 1) % (FPS + 1); 
	}

	public static void main(String[] args) {
		launch(args);
	}

}
