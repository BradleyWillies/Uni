package wmr;

import java.util.LinkedList;

public class SubLine {

	private LinkedList<Connection> connections;
	private Station[] termini;
	
	public SubLine() {
		connections = new LinkedList<Connection>();
		termini = new Station[2];
	}
	
	public LinkedList<Connection> getConnections() {
		return connections;
	}
	
	public Station[] getTermini() {
		return termini;
	}
	
	public void addConnection(Connection connection) {
		if (connection != null) {
			if (!connections.contains(connection)) {
				connections.add(connection);
			}
		}
	}
	
	public void addTerminus(Station terminus) {
		for (int i = 0 ; i < termini.length ; i++) {
			if (termini[i] == null) {
				termini[i] = terminus;
				break;
			}
		}
	}
	
}
