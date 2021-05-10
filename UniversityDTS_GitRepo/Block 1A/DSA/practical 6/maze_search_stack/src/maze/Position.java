package maze;

/**
 * Position.java : Represents a single position in a maze of characters. 
 *   
 * @author S H S Wong (Adapted form Lewis/Chase 2005)
 * @version 22-10-2020
 */
public class Position
{
	private int row;       // row number
	private int column;    // column number
	
	/**
	 * Constructor: Constructs a position using the parameter values.
	 * @param row number
	 * @param column number
	 */
	public Position(int row, int col) {
		this.row = row;
		this.column = col;
	}
	
	/**
	 * Returns the row number of this position.
	 * @return the row number of this position
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Returns the column number of this position.
	 * @return the column number of this position
	 */
	public int getCol() {
		return column;
	}
	
	/**
	 * Indicates whether some other object is "equal to" this one.
	 * If two Position objects equals to each other, 
	 * they have the same row and column numbers.
	 * 
	 * This method overrides the inherited equals method from class Object.
	 * 
	 * @param a given object
	 * @return true if the given object is a Position object 
	 * 	which has the same row and column numbers as this Position object;
	 * 	false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		
		if (obj instanceof Position) {
			Position another = (Position) obj;
			if (this.getRow() == another.getRow() && 
					this.getCol() == another.getCol()) {
				result = true;
			}
		}

		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("position: (%d,%d)", row, column);
	}
}
