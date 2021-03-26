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
	
	// board representation
	private Player[][] board = new Player[SIZE][SIZE];


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
		return board[column][row];
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
		if(board[column][row] == null) {
			board[column][row] = player;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the player who has won the game, or {@code null} if nobody has won
	 * yet.
	 *
	 * @return {@link Player} that won the game, or {@code null} if nobody won yet.
	 */
	public Player getWinner() {
		Player winner = null;
		// if winner by columns, return that
		winner = getWinnerInColumns();
		// if winner by rows, return that
		if (winner == null) {
			winner = getWinnerInRows();
		}
		// if winner by major diagonal, return that
		if (winner == null) {
			winner = getWinnerInMajorDiagonal();
		}
		// if winner by minor diagonal, return that
		if (winner == null) {
			winner = getWinnerInMinorDiagonal();
		}
		// no winner
		return winner;
	}
	
	private Player getWinnerInColumns() {		
		for (int iColumn = 0 ; iColumn < SIZE ; iColumn++) {
			Player player = getCell(iColumn, 0);				// check player at iColumn and first row
			if (player != null) {
				boolean hasWon = true;
				for (int iRow = 1 ; iRow < SIZE ; iRow++) {		// check the players in the rest of the rows skipping the first row
					if (getCell(iColumn, iRow) != player) {
						hasWon = false;
					}
				}
				if (hasWon) return player;
			}
		}
		return null;
	}
	
	private Player getWinnerInRows() {
		for (int iRow = 0 ; iRow < SIZE ; iRow++) {
			Player player = getCell(0, iRow);							// check player at first column and iRow
			if (player != null) {
				boolean hasWon = true;
				for (int iColumn = 1 ; iColumn < SIZE ; iColumn++) {	// check the players in the rest of the columns skipping the first column
					if (getCell(iColumn, iRow) != player) {
						hasWon = false;
					}
				}
				if (hasWon) return player;
			}
		}
		return null;
	}
	
	private Player getWinnerInMajorDiagonal() {
		Player firstCellPlayer = getCell(0, 0);									// get player at top left
		for (int i = 1 ; i < SIZE ; i++) {										
			Player currCellPlayer = getCell(i, i);								// get the cell at the next diagonal down and right (1,1) then (2,2) etc..
			if (currCellPlayer == null || currCellPlayer != firstCellPlayer) {
				return null;
			}
		}
		return firstCellPlayer;
	}
	
	private Player getWinnerInMinorDiagonal() {
		int iColumn = SIZE-1;
		Player firstCellPlayer = getCell(iColumn, 0);							// get player at top right
		for (int i = 1 ; i < SIZE ; i++) {
			Player currCellPlayer = getCell(iColumn-i, i);						// get the cell at the next diagonal down and left (2,0) then (1,1) then (0,2) etc..
			if (currCellPlayer == null || currCellPlayer != firstCellPlayer) {
				return null;
			}
		}
		return firstCellPlayer;
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
		
		for (int iRow = 0 ; iRow < SIZE ; iRow++) {
			for (int iColumn = 0 ; iColumn < SIZE ; iColumn++) {
				Player cell = getCell(iRow, iColumn);
				if (cell == null) {
					sb.append(".");
				} else {
					sb.append(cell.getName().charAt(0));
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
