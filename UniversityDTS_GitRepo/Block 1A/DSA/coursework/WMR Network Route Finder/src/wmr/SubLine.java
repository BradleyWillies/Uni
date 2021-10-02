package wmr;

import java.util.LinkedList;

/**
 * A SubLine represents a single branch of connected Stations within a Line
 * 
 * @author Bradley Willies
 * @version 27/06/2021
 */
public class SubLine {

	// an ordered list of Connections
	private LinkedList<Connection> connections;
	// the termini on this SubLine
	private Station[] termini;
	
	/**
	 * constructor which creates a SubLine, initialises the LinkedList of Connections and 
	 * initialises the array of Stations for the termini with 2 indexes, since there will be 
	 * 2 termini
	 */
	public SubLine() {
		connections = new LinkedList<Connection>();
		termini = new Station[2];
	}
	
	/**
	 * 
	 * @return the LinkedList of Connections for this SubLine
	 */
	public LinkedList<Connection> getConnections() {
		return connections;
	}
	
	/**
	 * 
	 * @return the array of Stations representing the termini for this SubLine
	 */
	public Station[] getTermini() {
		return termini;
	}
	
	/**
	 * Adds the provided Connection to the list of connections for this SubLine
	 * 
	 * @param connection
	 */
	public void addConnection(Connection connection) {
		if (connection != null) {
			if (!connections.contains(connection)) {
				connections.add(connection);
			}
		}
	}
	
	/**
	 * Adds the provided Station to the array of termini for this SubLine
	 * 
	 * @param terminus
	 */
	public void addTerminus(Station terminus) {
		for (int i = 0 ; i < termini.length ; i++) {
			if (termini[i] == null) {
				termini[i] = terminus;
				break;
			}
		}
	}
	
}
