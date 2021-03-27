package uk.ac.aston.jpd.tut1.tictactoe;

import java.util.ArrayList;

import uk.ac.aston.jpd.tut1.tictactoe.game.Board;
import uk.ac.aston.jpd.tut1.tictactoe.game.Player;
import uk.ac.aston.jpd.tut1.tictactoe.io.TextUI;

/**
 * Represents the game flow for Tic-Tac-Toe.
 */
public class Game {

	private Board board = new Board();
	private ArrayList<Player> players = new ArrayList<Player>();
	
	/**
	 * Plays a round of the game, and returns the winner {@link Player} if there is one.
	 *
	 * @param ui Text-based user interface that should be used to present the game to the players.
	 */
	public Player playRound(TextUI ui, Game game) {
		for (Player p : game.players) {									// for (each player)
			ui.writeMessage(board.toString());										//    show the board
			ui.writeMessage("It is " + p.getName() + "'s turn.");	//    say whose turn it is
			ui.prompt("Please provide a row and a column");
			
			boolean addedToCell = false;
			while (!addedToCell) {
				boolean goodCoords = false;
				int[] coords = ui.readNumbers(2);					//    ask for a column and a row
				while (!goodCoords) {
					if (coords != null) {
						goodCoords = true;
					} else {
						coords = ui.readNumbers(2);
					}
				}
				if(board.occupyCell(coords[0], coords[1], p)) {		//    try to occupy the cell, report if it was occupied or not
					addedToCell = true;
					ui.writeMessage("That cell was free, it is now occupied by " + p.getName());
				} else {
					ui.prompt("That cell was not free, please provide a different row and column");
				}
			}
			Player winner = board.getWinner();	
			if (winner != null) return winner;						//    if there is a winner, return it
		}
		return null;												// 	no winner this round
	}

	/**
	 * Returns {@code true} if the game has ended in a draw, {@code false} otherwise.
	 */
	public boolean isDraw() {
		// return false if there is at least one free space on the board
		for (int iColumn = 0 ; iColumn < Board.SIZE ; iColumn++) {
			for (int iRow = 0 ; iRow < Board.SIZE ; iRow++) {
				if (board.getCell(iColumn, iRow) == null) return false;
			}
		}
		return true;
	}

	/**
	 * Entry point to the application.
	 */
	public static void main(String[] args) {
		// set up game and UI
		Game game = new Game();
		TextUI ui = new TextUI();
		boolean playersSetup = false;
		while (!playersSetup) {
			ui.prompt("Please enter the name of player " + (game.players.size()+1));
			game.players.add(new Player(ui.readLine()));
			ui.writeMessage("Is there another player? (Y/N)");
			if (ui.readLine().equalsIgnoreCase("n")) playersSetup = true;
		}
		
		// keep playing rounds until there is a winner or the game ends in a draw
		boolean keepPlaying = true;
		Player winner = null;
		while (keepPlaying) {
			winner = game.playRound(ui, game);
			if (winner != null || game.isDraw()) keepPlaying = false;
		}
		
		// report winner
		ui.writeMessage(game.board.toString());
		if (game.isDraw()) {
			ui.writeMessage("GAME OVER --- DRAW");
		} else {
			ui.writeMessage("GAME OVER --- The winner is: " + winner.getName() + "!");
		}
	}
}
