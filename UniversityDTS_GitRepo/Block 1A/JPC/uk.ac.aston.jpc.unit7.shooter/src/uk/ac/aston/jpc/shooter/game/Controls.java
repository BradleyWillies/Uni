package uk.ac.aston.jpc.shooter.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

/**
 * Keeps track of which actions are being currently requested by the player in
 * the game. Translates physical keys (W or up key) to high-level commands
 * ("UP"). This allows us to have multiple keys associated with the same command,
 * and we could even add support for input from other devices.
 */
public class Controls {

	/** The player wants to move up. */
	public static final int ACTION_UP = 0;

	/** The player wants to move down. */
	public static final int ACTION_DOWN = ACTION_UP + 1;

	/** The player wants to move to the left. */
	public static final int ACTION_LEFT = ACTION_DOWN + 1;

	/** The player wants to move to the right. */
	public static final int ACTION_RIGHT = ACTION_LEFT + 1;

	/** The player wants to fire their ship's gun. */
	public static final int ACTION_FIRE = ACTION_RIGHT + 1;

	private static final Map<KeyCombination, Integer> KEY_TO_ACTION = new HashMap<>();

	static {
		// WASD
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.W), ACTION_UP);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.A), ACTION_LEFT);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.S), ACTION_DOWN);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.D), ACTION_RIGHT);
		
		// Arrow Keys
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.UP), ACTION_UP);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.LEFT), ACTION_LEFT);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.DOWN), ACTION_DOWN);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.RIGHT), ACTION_RIGHT);
		
		// Num pad
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.KP_UP), ACTION_UP);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.KP_LEFT), ACTION_LEFT);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.KP_DOWN), ACTION_DOWN);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.KP_RIGHT), ACTION_RIGHT);
		
		// Spacebar
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.SPACE), ACTION_FIRE);
	}

	private final Map<Integer, Boolean> actionActive = new HashMap<>();

	public Controls(Scene scene) {
		// V1 ---> 
		/*
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ev) {
				for (Entry<KeyCombination, Integer> entry : KEY_TO_ACTION.entrySet()) {
					final KeyCombination kc = entry.getKey();
					final int action = entry.getValue();
					if (kc.match(ev)) {
						actionActive.put(action, true);
						break;
					}
				}
			}
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ev) {
				for (Entry<KeyCombination, Integer> entry : KEY_TO_ACTION.entrySet()) {
					final KeyCombination kc = entry.getKey();
					final int action = entry.getValue();
					if (kc.match(ev)) {
						actionActive.put(action, false);
						break;
					}
				}
			}
		});
		*/
		
		// V2 --->
		class KeyMatchingHandler implements EventHandler<KeyEvent> {
			private boolean pressOrRelease;
			
			public KeyMatchingHandler(boolean pressOrRelease) {
				this.pressOrRelease = pressOrRelease;
			}
			
			@Override
			public void handle(KeyEvent ev) {
				for (Entry<KeyCombination, Integer> entry : KEY_TO_ACTION.entrySet()) {
					final KeyCombination kc = entry.getKey();
					final int action = entry.getValue();
					if (kc.match(ev)) {
						actionActive.put(action, pressOrRelease);
						break;
					}
				}
			}
		}
		
		scene.setOnKeyPressed(new KeyMatchingHandler(true));
		scene.setOnKeyReleased(new KeyMatchingHandler(false));
	}

	/**
	 * Returns {@code true} if and only if the specified action is being currently
	 * requested by the player.
	 */
	public boolean isActive(int action) {
		return actionActive.getOrDefault(action, false);
	}

}
