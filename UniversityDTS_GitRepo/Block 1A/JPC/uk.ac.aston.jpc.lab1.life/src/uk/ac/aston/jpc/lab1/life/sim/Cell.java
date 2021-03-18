package uk.ac.aston.jpc.lab1.life.sim;

import java.util.ArrayList;

/**
 * Represents a cell in Conway's Game of Life. The cell may be alive or dead,
 * and it will have a certain set of neighbours in the {@link World} it belongs
 * to.
 * 
 * @author bwillies
 * @since 1.0
 */
public class Cell {
	// Fields
	private final World world;
	private final int row;
	private final int column;
	private boolean isAlive;	// default value is false

	// Constructor
	/**
	 * Constructs a cell object and sets the fields
	 * 
	 * @param world The world the cell belongs to
	 * @param row The row in the world grid the cell is in
	 * @param column The column in the world grid the cell is in
	 */
	public Cell(World world, int row, int column) {
		this.world = world;
		this.row = row;
		this.column = column;
	}
	
	// Accessors
	/**
	 * 
	 * @return The world object for this cell
	 */
	public World getWorld() {
		return world;
	}
	
	/**
	 * 
	 * @return The row number this cell is in the world grid
	 */
	public int getRow() {
		return row;
	}

	/**
	 * 
	 * @return The column number this cell is in the world grid
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * 
	 * @return Whether this cell is alive or not
	 */
	public boolean isAlive() {
		return isAlive;
	}
	
	// Mutators
	/**
	 * Sets the cell to be alive or not
	 * 
	 * @param isAlive true if the cell is alive, false if it is dead
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	// More behaviour
	/**
	 * 
	 * @return An array list of the neighbours for this cell
	 */
	public ArrayList<Cell> getNeighbours(){
		ArrayList<Cell> neighbours = new ArrayList<Cell>();
		
		int above = row - 1;
		int below = row + 1;
		int left = column - 1;
		int right = column + 1;
		
		if (row > 0 && column > 0) { neighbours.add(world.getCell(above, left)); } 										// Add cell above and left
		if (row > 0) { neighbours.add(world.getCell(above, column)); } 													// Add cell above
		if (row > 0 && column + 1 < world.getColumns()) { neighbours.add(world.getCell(above, right)); } 					// Add cell above and right
		
		if (column > 0) { neighbours.add(world.getCell(row, left)); }													// Add cell left
		if (column + 1 < world.getColumns()) { neighbours.add(world.getCell(row, right)); }								// Add cell right
		
		if (row + 1 < world.getRows() && column > 0) { neighbours.add(world.getCell(below, left)); }						// Add cell below and left
		if (row + 1 < world.getRows()) { neighbours.add(world.getCell(below, column)); }									// Add cell below
		if (row + 1 < world.getRows() && column + 1 < world.getColumns()) { neighbours.add(world.getCell(below, right)); }	// Add cell below and right
		
		return neighbours;
	}
	
	/**
	 * 
	 * @return The number of neighbours for this cell which are alive
	 */
	public int countAliveNeighbours() {
		int aliveCount = 0;
		
		for (Cell cell : getNeighbours()) {
			if (cell.isAlive()) {
				aliveCount++;
			}
		}
				
		return aliveCount;
	}
	
	/**
	 * 
	 * @return A string containing x if the cell is alive or a space if it is dead
	 */
	@Override
	public String toString() {
		return isAlive ? "x" : " ";
	}
}
