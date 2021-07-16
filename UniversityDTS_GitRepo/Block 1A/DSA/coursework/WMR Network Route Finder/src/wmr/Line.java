package wmr;

import java.util.HashSet;

/**
 * A Line represents a collection of Stations which come one after the other.
 * A Line is made up of SubLines which contains these collections of stations.
 * 
 * @author Bradley Willies
 * @version 27/06/2021
 */
public class Line {

	// fields containing the name of the line and a set of the sublines
	private String name;
	private HashSet<SubLine> subLines;
	
	/**
	 * Constructor which creates the Line with the provided name and initialises the 
	 * HashSet of SubLines
	 * 
	 * @param name
	 */
	public Line(String name) {
		this.name = name;
		subLines = new HashSet<SubLine>();
	}
	
	/**
	 * 
	 * @return the name of this Line
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return The HashSet of SubLines in this Line
	 */
	public HashSet<SubLine> getSublines() {
		return subLines;
	}
	
	/**
	 * Adds a SubLine to the HashSet of SubLines for this Line
	 * 
	 * @param subLine
	 */
	public void addSubline(SubLine subLine) {
		subLines.add(subLine);
	}
	
}
