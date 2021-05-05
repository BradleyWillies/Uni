package uk.ac.aston.jpc.shooter;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import uk.ac.aston.jpc.shooter.game.Game;

/**
 * Main class for the Shapes lab.
 */
public class Launcher extends Application {

	private Canvas canvas;
	private Game game;

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

		stage.setResizable(false);
		stage.setScene(new Scene(root));
		stage.show();

		game = new Game(canvas);
		game.draw();
		Timeline drawEverySecond = new Timeline(
			new KeyFrame(Duration.millis(1000/Game.FPS), (event) -> {
				game.tick();
				game.draw();
			})
		);
		drawEverySecond.setCycleCount(Timeline.INDEFINITE);
		drawEverySecond.play();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
