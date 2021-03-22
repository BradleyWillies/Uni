package uk.ac.aston.jpd.tut1.tictactoe;

import uk.ac.aston.jpd.tut1.tictactoe.game.Board;
import uk.ac.aston.jpd.tut1.tictactoe.game.Player;
import uk.ac.aston.jpd.tut1.tictactoe.io.TextUI;

/**
 * Represents the game flow for Tic-Tac-Toe.
 */
public class Game {

	/**
	 * Plays a round of the game, and returns the winner {@link Player} if there is one.
	 *
	 * @param ui Text-based user interface that should be used to present the game to the players.
	 */
	public Player playRound(TextUI ui) {
		// for (each player) {
		//    show the board
		//    say whose turn it is
		//    ask for a column and a row
		//
		//    try to occupy the cell, report if it was occupied or not
		//
		//    if there is a winner, return it
		// }
		//
		// no winner yet
	}

	/**
	 * Returns {@code true} if the game has ended in a draw, {@code false} otherwise.
	 */
	public boolean isDraw() {
		// return false if there is at least one free space on the board
	}

	/**
	 * Entry point to the application.
	 */
	public static void main(String[] args) {
		// set up game and UI

		// keep playing rounds until there is a winner or the game ends in a draw
		
		// report winner
	}
}
