/**
 * 
 */
package maze;

import java.util.List;

import stack.LinkedStack;
import stack.StackADT;

/**
 * PathFinder can be used to locate a path from the entrance to the exit
 * of a given maze. 
 * 
 * @author S H S Wong
 * @version 22-10-2020
 */
public class PathFinder {
	
	private Maze maze;  // the maze within which the PathFinder operates
	
	/* ++++
	 * A stack to keep track of the positions along the path
	 * from the entrance, e.g. (0,0), to the exit, e.g. (8,12),
	 * of the maze. Each position is marked in the form of: (row,column).
	 * 
	 * The positions along the path are to be kept in a reversed order.
	 * Hence, when the maze traversal has completed, 
	 * the top of this stack should keep the position of the exit. 
	 */
	private StackADT<Position> reversedPath;
	
	/**
	 * Constructor: 
	 * Creates a PathFinder object whose goal is to find a path 
	 * from the entrance to the exit in the given maze.
	 * 
	 * @param maze
	 */
	public PathFinder(Maze maze) 
	{
		/* !!!!
		 * Initialise the fields for this PathFinder object using
		 * the given Maze object and an empty stack. 
		 */
		
		
	}
	
	/**
	 * Attempts to find a path by iteratively traversing the maze.  
	 * During the maze traversal, records the positions that make up 
	 * a path connecting the entrance and the exit of the maze.
	 * 
	 * At each move, special characters are used to indicate locations 
	 * 	that have been tried and that eventually become part of the solution.  
	 * This method uses a stack to keep track of the possible moves 
	 * 	that could be made.
	 * 
	 * Returns a stack containing positions from the entrance to the exit.
	 * An empty stack indicates that no path is found.
	 * 
	 * @return a sequence of positions from entrance to exit represented as a stack
	 */
	public StackADT<Position> findPath()
	{
		/* ++++
		 * To keep track of whether the exit has been found.
		 */ 
		boolean found = false;
		
		/* !!!!
		 * Record the entrance in the path stack.
		 */ 
		
		
		/* !!!! 
		 * Mark the entrance as visited.
		 */
		
		
		/* ++++
		 * Traverse the maze by moving from one position to another. 
		 * At each move, it is possible to move to the position:
		 * - on the left
		 * - on the right
		 * - below
		 * - above 
		 * which exists in the maze and is not blocked.
		 * The traversal is continued until:
		 * - the end position is reached, or
		 * - every possible position has been checked but no path can be found.
		 */
		while (!found && !reversedPath.isEmpty())
		{
			/* !!!!
			 * Look up the top element in the path stack 
			 * 	(i.e. the current position within the maze).
			 */
			

			/* !!!!
			 * Determine if the exit has been found or 
			 * 	further maze traversal is needed.
			 */
			
			
			
			else {
				/* !!!!
				 * 1. Get a list of the potential valid moves from the current position.
				 * 2. Check the number of potential moves found.
				 *    - Nothing found means that we have reached a dead end.
				 *    - Found at least one potential move means that 
				 *      we can continue the traversal using one of the potential moves.
				 * 3. When we reach a dead end, simply remove the current move
				 *     from the path record and 
				 *     let the traversal to try an alternate path in the next iteration. 
				 * 4. When making any move, don't forget to mark the position as visited.   
				 */
				
				
				
				
				
			}
		}
		/* ++++
		 * Returns a sequence of positions from the entrance to the exit.
		 */
		return reverse();
	}

	/* 
	 * Returns a stack of positions which represents 
	 * 	a path from the entrance to the exit within the maze.
	 *  
	 * @return a stack of positions that made up a path
	 *      from the entrance to the exit
	 *      (i.e. the top of the stack keeps the entrance position)
	 */	
	private StackADT<Position> reverse()
	{
		/* !!!!
		 * 1. Create a new stack object for keeping the resulting path.
		 * 2. Pop an element from the reversed path stack and 
		 *    put it in the result stack.
		 * 3. Repeat Step (2) until no more elements to work with.
		 * 4. Return the result stack.  
		 */
		
		
		
	}

	/**
	 * Main - serves as a tester method
	 */
	public static void main(String[] args)
	{
		// Create a PathFinder object for exploring a maze
		PathFinder pathFinder = new PathFinder(new Maze());
		// Find a path in the maze and print it out.
		System.out.println(pathFinder.findPath().toString());
	}
}
