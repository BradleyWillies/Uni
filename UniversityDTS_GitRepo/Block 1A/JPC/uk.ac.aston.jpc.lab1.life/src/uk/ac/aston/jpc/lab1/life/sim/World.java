package uk.ac.aston.jpc.lab1.life.sim;

/**
 * Represents a world, or grid, in Conway's Game of Life. The world has 
 * a grid of cells {@link Cell} which can be alive or dead.
 * 
 * @author bwillies
 * @since 1.0
 */
public class World {
	// Fields
	private final int nRows;
	private final int nColumns;
	private final Cell[] cells;
	
	// Constructor
	/**
	 * Constructs a world object and populates the grid of cells.
	 * 
	 * @param nRows Number of rows in the grid
	 * @param nColumns Number of columns in the grid
	 */
	public World(int nRows, int nColumns) {
		this.nRows = nRows;
		this.nColumns = nColumns;
		
		this.cells = new Cell[nRows * nColumns]; // All Cells are default value - null
		
		int iCell = 0;
		for (int row = 0 ; row < nRows ; row++) {
			for (int column = 0 ; column < nColumns ; column++) {
				cells[iCell] = new Cell(this, row, column);
				iCell++;
			}
		}
	}
	
	// Convenience constructor
	/**
	 * Uses the original constructor to create the grid, then sets the cells
	 * which are't a space character to be alive.
	 * 
	 * @param rows A text-based visual representation of a grid containing spaces or crosses
	 * representing dead or alive cells.
	 */
	public World(String... rows) {
		this(rows.length, rows[0].length());
		
		int iCell = 0;
		for (int row = 0 ; row < nRows ; row++) {
			for (int column = 0 ; column < nColumns ; column++) {
				if (rows[row].charAt(column) != ' ') {
					cells[iCell].setAlive(true);
				}
				iCell++;
			}
		}
	}

	// Accessors
	/**
	 * 
	 * @return Number of rows.
	 */
	public int getRows() {
		return nRows;
	}

	/**
	 * 
	 * @return Number of columns.
	 */
	public int getColumns() {
		return nColumns;
	}
		
	/**
	 * Gets the cell at the specified point in the grid.
	 * 
	 * @param row The row location
	 * @param column The Column location
	 * @return The Cell at the point in the grid (row x column)
	 */
	public Cell getCell(int row, int column) {
		return cells[row * nColumns + column];	// maps the 2D (row, column) to a 1D position
	}
	
	// More behaviour
	/**
	 * Creates a world object using the logic of the game to set each cell in the new 
	 * world to be alive or dead.
	 * 
	 * @return New world with updated cells
	 */
	public World nextGeneration() {
		World nextWorld = new World(nRows, nColumns);
		
		for (int i = 0 ; i < nRows ; i++) {
			for (int j = 0 ; j < nColumns ; j++) {
				Cell oldCell = this.getCell(i, j);
				Cell newCell = nextWorld.getCell(i, j);
				
				if (oldCell.isAlive()) {
					if (oldCell.countAliveNeighbours() == 2 || oldCell.countAliveNeighbours() == 3) {
						newCell.setAlive(true);
					}
				} else {
					if (oldCell.countAliveNeighbours() == 3) {
						newCell.setAlive(true);
					}
				}
			}
		}
		
		return nextWorld;
	}
	
	/**
	 * Creates a text-based visual representation of the grid with alive cells marked as a cross 
	 * and dead cells a space.
	 * 
	 * @returns A string containing the text-based visual representation of the world
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		int iColumn = 0;
		for (Cell cell : cells) {
			sb.append(cell.toString());
			iColumn++;
			if (iColumn == nColumns) {
				iColumn = 0;
				sb.append(System.lineSeparator());
			}
		}
		
		return sb.toString();
	}
	
}
