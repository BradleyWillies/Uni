package maze;

import java.util.ArrayList;
import java.util.List;

import stack.StackADT;


/**
 * Maze.java : Represents a maze of characters. 
 * The goal is to get from the top left corner to the bottom right, 
 * following a path of 1's.
 * 
 * @author S H S Wong & A Barnes   (Adapted from Lewis/Chase 2005)
 * @version 22-10-2020
 */
public class Maze
{
	// For Marking a location within the maze that has been visited.
	private static final int VISITED = 3;
	// For marking the final path through the maze
	private static final int PATH = 2;

	/* ++++ 
	 * The maze as represented by a 2D grid.
	 * A cell with value 1 represents a path.
	 * A cell with value 0 represents a blockage (i.e. a wall).
	 */
	private int[][] grid;
	
	/**
	 * Default Constructor:
	 * Initialise the grid with a default maze structure.
	 */
	public Maze(){
		/* ++++ 
		 * Specify a default maze structure.
		 * The maze as represented by a 2D grid.
		 * A cell with value 1 represents a path.
		 * A cell with value 0 represents a blockage (i.e. a wall).
		 */		
		int[][] maze = 
			{ 
				{1,1,1,0,1,1,0,0,0,1,1,1,1},
				{1,0,0,1,1,0,1,1,1,1,0,0,1},
				{1,1,1,1,1,0,1,0,1,0,1,0,0},
				{0,0,0,0,1,1,1,0,1,0,1,1,1},
				{1,1,1,0,1,1,1,0,1,0,1,1,1},
				{1,0,1,0,0,0,0,1,1,1,0,0,1},
				{1,0,1,1,1,1,1,1,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1} 
			};
		
		grid = maze;
	}
	
	/**
	 * Constructor: create a maze with walls and paths
	 * @param maze - a 2D grid of int values
	 */
	public Maze(int[][] maze) {
		grid = maze;
	}
	
	/**
	 * Returns the entrance of the maze as a Position object.
	 * @return the entrance 
	 */
	public Position entrance() {
		return new Position(0,0);
	}
	
	/**
	 * Returns the exit of the maze as a Position object.
	 * @return the exit
	 */
	public Position exit() {
		return new Position(8, 12);
	}
	
	/**
	 * Marks a position as being visited.
	 * @param pos	the position that is being visited now
	 */
	public void markVisited(Position pos) {
		grid[pos.getRow()][pos.getCol()] = VISITED;
	}
	
	/**
	 * Returns the potential valid moves from a given position within the maze
	 *  
	 * @param pos	a position in the maze
	 * @return a list of valid moves to the adjacent cells
	 */
	public List<Position> potentialMovesFrom(Position pos) {
		List<Position> moves = new ArrayList<>(4);
		
		int x = pos.getRow();
		int y = pos.getCol();
		
		/* ++++
		 * Identify 4 adjacent positions for the given position.
		 */
		Position[] positions = new Position[4];
		positions[0] = new Position(x, y+1);     // right
		positions[1] = new Position(x, y-1);     // left
		positions[2] = new Position(x+1, y);     // below
		positions[3] = new Position(x-1, y);     // above
		
		/* ++++
		 * Check each adjacent position to see if 
		 * 	it can be a potential move.
		 * If so, add it to the result set.
		 */
		for (Position p : positions) {
			if (valid(p)) {
				moves.add(p);
			}			
		}

		return moves;
	}
	
	/*
	 * Determines if a specific location is valid.
	 * Returns true if the given location is:
	 * - within the grid,
	 * - is not blocked (i.e. is not part of a wall),
	 * - has not been visited yet.
	 * 
	 * @param a position 
	 * @return
	 */
	private boolean valid (Position pos){
		boolean result = false;
		
		/* Check if the cell is in the bounds of the grid */
		if (pos.getRow() >= 0 && pos.getRow() < grid.length &&
				pos.getCol() >= 0 && 
				pos.getCol() < grid[pos.getRow()].length) {
			/* Check if the cell is not blocked and not previously tried */
			if (grid[pos.getRow()][pos.getCol()] == 1) {
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * Returns the maze as a string.
	 * 
	 * @return a string representation of the current state of the maze 
	 */
	@Override
	public String toString () {
		String result = "\n";
		
		// Print the grid line by line 
		for (int row=0; row < grid.length; row++) {
			// Print each element in a line
			for (int column=0; column < grid[row].length; column++)
				result += grid[row][column] + "";
			
			result += "\n";
		}
		
		return result;
	}

	/**
	 * Returns a string representation of the maze after traversal 
	 * 	with the path from entrance to exit clearly marked on the maze.
	 * @param path
	 * @return the maze after the traversal and the path found, if any
	 */
	public String showPath(StackADT<Position> path) {
		StringBuilder pathInfo = new StringBuilder("\n");
		StringBuilder thePath = new StringBuilder("\n");
		
		if (path.isEmpty()) {
			pathInfo.append("Lost in maze: No path found!");
		}
		else {
			thePath.append("The path:\n");
			/* ++++
			 *  For each position in path, 
			 *  set the content of the respective cell to PATH 
			 */
			while (!path.isEmpty()) {
				Position pos = path.pop();
				
				// update the maze to show the path found
				grid[pos.getRow()][pos.getCol()] = PATH;
				
				// gather the sequence of position(s) which make up the path
				thePath.append(pos.toString());
				if (!path.isEmpty()) {
					thePath.append(" -> ");
				}
			}
			
			// Print the grid line by line 
			for (int row=0; row < grid.length; row++){
			   // Print each element in a line
				for (int column=0; column<grid[row].length; column++){
					pathInfo.append(grid[row][column]);
				}
				pathInfo.append("\n");
			}
			
			// append the sequence of positions that make up the path to the output
			pathInfo.append(thePath.toString());
		}
		
		return pathInfo.toString();
	}

}
