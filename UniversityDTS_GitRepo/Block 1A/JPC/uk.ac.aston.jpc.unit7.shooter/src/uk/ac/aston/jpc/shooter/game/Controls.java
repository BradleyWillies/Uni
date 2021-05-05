package uk.ac.aston.jpc.shooter.game;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;

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

	// TODO add static initialisation block

	private final Map<Integer, Boolean> actionActive = new HashMap<>();

	public Controls(Scene scene) {
		// TODO need to set up key handlers here with a local class
	}

	/**
	 * Returns {@code true} if and only if the specified action is being currently
	 * requested by the player.
	 */
	public boolean isActive(int action) {
		return actionActive.getOrDefault(action, false);
	}

}
