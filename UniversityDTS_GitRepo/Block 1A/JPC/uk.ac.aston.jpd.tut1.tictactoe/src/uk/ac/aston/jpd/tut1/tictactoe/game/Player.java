package uk.ac.aston.jpd.tut1.tictactoe.game;

/**
 * Represents a player in the game tictactoe.
 * 
 * @author bwillies
 *
 */
public class Player {
	private final String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * @return The name of the player
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return ("This is a Player with the name: " + name);
	}
}
