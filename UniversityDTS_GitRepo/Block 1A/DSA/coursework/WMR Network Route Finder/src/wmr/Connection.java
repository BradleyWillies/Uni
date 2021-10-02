package wmr;

/**
 * A Connection is the link from one station to another within a railway line.
 * It knows the travel time between the stations.
 * 
 * @author Bradley Willies
 * @version 27/06/2021
 */
public class Connection {

	// Fields containing the connecting stations and the time to travel between them
	private Station fromToStation;
	private Station toFromStation;
	private int travelTime;
	
	/**
	 * Constructor which takes two stations and a time to travel between them and 
	 * creates the Connection for that information
	 * 
	 * @param fromToStation
	 * @param toFromStation
	 * @param travelTime
	 */
	public Connection(Station fromToStation, Station toFromStation, int travelTime) {
		this.fromToStation = fromToStation;
		this.toFromStation = toFromStation;
		this.travelTime = travelTime;
	}
	
	/**
	 * 
	 * @return the fromToStation in this Connection
	 */
	public Station getFromToStation() {
		return fromToStation;
	}
	
	/**
	 * 
	 * @return the toFromStation in this Connection
	 */
	public Station getToFromStation() {
		return toFromStation;
	}
	
	/**
	 * 
	 * @return the travelTime between the two stations in this Connection
	 */
	public int getTravelTime() {
		return travelTime;
	}
	
}
