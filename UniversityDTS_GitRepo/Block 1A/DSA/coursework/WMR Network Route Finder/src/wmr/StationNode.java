package wmr;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * A StationNode represents a single node within a collection of StationNodes. A StationNode 
 * holds a reference to a Station and knows which Lines it is on, which StationNodes it is 
 * connected to, and whether it has been visited by some collection (for node traversal).
 * 
 * @author Bradley Willies
 * @version 27/06/2021
 */
public class StationNode {
	
	// the station this node references
	private Station station;
	
	// a set of the names of the lines the Station in this node is on
	private HashSet<String> lineNames = new HashSet<String>();
	
	// a HashMap linking the name of a line to a set of connecting StationNodes
	private HashMap<String, HashSet<StationNode>> lineStationNodes = new HashMap<String, HashSet<StationNode>>();
	
	// a HashMap linking the name of a line to the ordered list of times this node's Station arrives at
	private HashMap<String, ArrayList<String>> arrivalTimes = new HashMap<String, ArrayList<String>>();
	
	// a boolean value used during node traversal to represent whether this node has been visited or not
	private boolean visited = false;
	
	/**
	 * constructor which creates a StationNode with a reference to the provided Station
	 * 
	 * @param station
	 */
	public StationNode(Station station) {
		this.station = station;
	}
	
	/**
	 * 
	 * @return the Station this node references
	 */
	public Station getStation() {
		return station;
	}
	
	/**
	 * 
	 * @return the HashMap of line names to list of arrival times for this StationNode's Station
	 */
	public HashMap<String, ArrayList<String>> getArrivalTimes() {
		return arrivalTimes;
	}
	
	/**
	 * adds the provided line name to the HashSet of lines this StationNode's Station is on
	 * 
	 * @param lineName
	 */
	public void addLineName(String lineName) {
		lineNames.add(lineName);
	}
	
	/**
	 * 
	 * @return the set of line names this StationNode's Station is on
	 */
	public HashSet<String> getLineNames() {
		return lineNames;
	}
	
	/**
	 * Adds the provided StationNode to the set of Station nodes mapped to the provided line name
	 * 
	 * @param lineName
	 * @param connectedNode
	 */
	public void addLineStationNode(String lineName, StationNode connectedNode) {
		// if the line doesn't exist in lineStationNodes, add the new line with an empty HashSet
		if (!lineStationNodes.containsKey(lineName)) {
			lineStationNodes.put(lineName, new HashSet<StationNode>());
		}
		// add the connecting StationNode to the HashSet for the line name
		lineStationNodes.get(lineName).add(connectedNode);
	}

	/**
	 * Calculates the arrival times for this StationNode's Station given the provided line, the time 
	 * trains start and end service, and the travel time from the first node on the line to this node
	 * 
	 * @param travelTime
	 * @param openTime
	 * @param closeTime
	 * @param lineName
	 */
	public void addArrivalTimes(int travelTime, String openTime, String closeTime, String lineName) {
		// add the lineName to the arrivalTimes hashmap if it doesn't exist
		if (!arrivalTimes.containsKey(lineName)) {
			arrivalTimes.put(lineName, new ArrayList<String>());
		}
		// create the open and close times as LocalTime objects to perform operations on
		LocalTime openTimeObject = LocalTime.parse(openTime);
		LocalTime lastArrivalTime = LocalTime.parse(closeTime).plusMinutes(travelTime);
		// add the travel time to the open time object
		openTimeObject = openTimeObject.plusMinutes(travelTime);
		// while the open time is before or equal to the close time plus the arrival time (to account for the last train)
		while (!openTimeObject.equals(lastArrivalTime)) {
			// add the open time object to the arrival times
			arrivalTimes.get(lineName).add(openTimeObject.toString());
			// add the interval time (15 minutes) to the open time object
			openTimeObject = openTimeObject.plusMinutes(15);
		}
		// add the final arrival time
		arrivalTimes.get(lineName).add(openTimeObject.toString());
	}
	
	/**
	 * changes the boolean visited to true to show that this node has been visited
	 * while traversing the graph of nodes
	 */
	public void markVisited() {
		visited = true;
	}
	
	/**
	 * changes visited to false, the original state
	 */
	public void resetVisited() {
		visited = false;
	}
	
	/**
	 * returns a boolean saying whether this node has been visited or not
	 * @return boolean
	 */
	public boolean isVisited() {
		return visited;
	}
	
	/**
	 * returns the hashmap of line name to hash set of station nodes
	 * @return
	 */
	public HashMap<String, HashSet<StationNode>> getLineStationNodes() {
		return lineStationNodes;
	}

}
