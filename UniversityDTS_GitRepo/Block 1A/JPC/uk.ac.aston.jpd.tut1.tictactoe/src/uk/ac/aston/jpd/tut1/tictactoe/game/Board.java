package uk.ac.aston.jpd.tut1.tictactoe.game;

/**
 * Represents the 3x3 board in a Tic-Tac-Toe game. The players mark positions in
 * the board, and the winner is the first player that occupies an entire row, an
 * entire column, or one of the diagonals.
 *
 * @author Antonio García-Domínguez
 */
public class Board {

	/**
	 * Number of rows and columns for the Tic-Tac-Toe board.
	 */
	public static final int SIZE = 3;


	/**
	 * Returns the {@link Player} who is occupying the cell at column {@code column}
	 * and row {@code row}, or {@code null} if no {@link Player} is occupying it.
	 *
	 * @param column Zero-based index for the column.
	 * @param row    Zero-based index for the row.
	 * @return {@link Player} occupying the position, or {@code null} if no
	 *         {@link Player} is occupying it.
	 */
	public Player getCell(int column, int row) {
		// TODO
	}

	/**
	 * If the cell at column {@code column} and row {@code row} is free, marks it as
	 * occupied by {@code player} and returns {@code true}. Otherwise, it returns
	 * {@code false}.
	 *
	 * @param column Zero-based index for the column.
	 * @param row    Zero-based index for the row.
	 * @param player {@link Player} that is trying to occupy the cell.
	 * @return {@code true} if the cell was not occupied until now, or {@code false}
	 *         if the cell was already occupied.
	 */
	public boolean occupyCell(int column, int row, Player player) {
		// TODO
	}

	/**
	 * Returns the player who has won the game, or {@code null} if nobody has won
	 * yet.
	 *
	 * @return {@link Player} that won the game, or {@code null} if nobody won yet.
	 */
	public Player getWinner() {
		// if winner by columns, return that
		// if winner by rows, return that
		// if winner by major diagonal, return that
		// if winner by minor diagonal, return that
		// no winner
	}

	/**
	 * Returns {@code true} if there is a free space on the board. Otherwise,
	 * returns {@code false}.
	 *
	 * @return {@code true} if there is a free space on the board, {@code false}
	 *         otherwise.
	 */
	public boolean hasSpace() {
		// TODO complete this method
		return false;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		// for (every row) {
		//   for (every column) {
		//     get cell
		//     sb.append('.' if empty, otherwise initial of player);
		//   }
		//   append line separator
		// }
		
		return sb.toString();
	}

}
