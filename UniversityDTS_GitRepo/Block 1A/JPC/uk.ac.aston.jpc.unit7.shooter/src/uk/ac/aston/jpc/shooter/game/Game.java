package uk.ac.aston.jpc.shooter.game;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import uk.ac.aston.jpc.shooter.game.entities.Bullet;
import uk.ac.aston.jpc.shooter.game.entities.EnemyShip;
import uk.ac.aston.jpc.shooter.game.entities.PlayerShip;
import uk.ac.aston.jpc.shooter.game.graphics.SpriteSheet;

/**
 * Loads assets, sets up the initial state and coordinates between the various
 * game entities. Made up of a single screen, where your ship can shoot down a
 * number of static objects.
 */
public class Game {

	/**
	 * Number of frames per second that this game should run at. Animations should
	 * have a number of frames that this constant is divisible against.
	 */
	public static final int FPS = 24;
	private static final String SPRITESHEET_FILENAME = "spritesheet.png";

	private final Canvas canvas;
	private final Controls controls;
	private final SpriteSheet spriteSheet;
	private Set<GameEntity> entities = new HashSet<>();

	public Game(Canvas canvas) {
		this.canvas = canvas;
		this.controls = new Controls(canvas.getScene());
		this.spriteSheet = loadSpriteSheet();

		placePlayerShip(canvas);
		generateEnemyShips(canvas);
	}

	/**
	 * Returns the spritesheet with all the graphics in the game. The
	 * spritesheet is a single image divided into regions, where each
	 * region is an animation frame for one of our sprites.
	 */
	private SpriteSheet loadSpriteSheet() {
		return new SpriteSheet.Builder(getSpriteSheetImage())
				.ofSize(16, 16)
				.at(0, 0).region(Bullet.REGIONS[0])
				.at(16, 0).region(Bullet.REGIONS[1])
				.at(32, 0).region(Bullet.REGIONS[2])
				.at(0, 16).region(Bullet.REGIONS[3])
				.at(16, 16).region(Bullet.REGIONS[4])
				.at(32, 16).region(Bullet.REGIONS[5])
				.at(0, 32).region(Bullet.REGIONS[6])
				.at(16, 32).region(Bullet.REGIONS[7])
				.ofSize(24,  46)
				.at(48,  0).region(EnemyShip.REGION)
				.ofSize(32, 42)
				.at(72, 0).region(PlayerShip.REGION)
				.build();
	}

	/**
	 * Places the player ship centered at the bottom of the screen.
	 */
	private void placePlayerShip(Canvas canvas) {
		PlayerShip playerShip = new PlayerShip(spriteSheet, controls);
		addEntity(playerShip);
		playerShip.setXY(
			canvas.getWidth() / 2 - playerShip.getWidth()/2,
			canvas.getHeight() - playerShip.getHeight() - 50);
	}

	/**
	 * Create a random number of enemies on the top half of the screen.
	 */
	private void generateEnemyShips(Canvas canvas) {
		final Random rnd = new Random();
		final int nEnemies = 5 + rnd.nextInt(15);
		for (int i = 0; i < nEnemies; i++) {
			final double enemyX = rnd.nextDouble() * canvas.getWidth();
			final double enemyY = rnd.nextDouble() * canvas.getHeight() / 2;

			EnemyShip enemyShip = new EnemyShip(spriteSheet);
			enemyShip.setXY(enemyX, enemyY);
			addEntity(enemyShip);
		}
	}

	/**
	 * Returns the spritesheet with all the graphics for this game.	 
	 */
	public SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}

	/**
	 * Adds an entity to the game. Returns {@code true} if and only if the entity was not already present.
	 */
	public boolean addEntity(GameEntity entity) {
		return entities.add(entity);
	}

	/**
	 * Removes an entity from the game. Returns {@code true} if and only if the entity was present.
	 */
	public boolean removeEntity(GameEntity entity) {
		return entities.remove(entity);
	}

	/**
	 * Returns the set of entities that collide with this one.
	 */
	public Set<GameEntity> collidingWith(GameEntity entity) {
		// TODO need to use streams and lambdas here to detect colliding entities
		return Collections.emptySet();
	}

	/**
	 * Clears the screen and draws all the entities.
	 */
	public void draw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

//		entities.forEach(new Consumer<GameEntity>() {
//			@Override
//			public void accept(GameEntity t) {
//				t.draw(gc);
//			}
//		});
		
		entities.forEach(e -> e.draw(gc));
	}

	/**
	 * Advances the state of all entities by one tick.
	 */
	public void tick() {
		Set<GameEntity> entitiesCopy = new HashSet<>(entities);
		
		entitiesCopy.forEach(e -> e.tick(this));
	}

	/**
	 * Returns the JavaFX {@link Image} with all the graphics for our game.
	 */
	private Image getSpriteSheetImage() {
		return new Image(SpriteSheet.class.getResourceAsStream(SPRITESHEET_FILENAME));
	}
}
