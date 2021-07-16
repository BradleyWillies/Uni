package wmr;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StationNode {
	
	// the station this node references
	private Station station;
	private HashSet<String> lineNames = new HashSet<String>();
	private HashMap<String, HashSet<StationNode>> lineStationNodes = new HashMap<String, HashSet<StationNode>>();
	private HashMap<String, ArrayList<String>> arrivalTimes = new HashMap<String, ArrayList<String>>();
	private boolean visited = false;
	
	public StationNode(Station station) {
		this.station = station;
	}
	
	public Station getStation() {
		return station;
	}
	public HashMap<String, ArrayList<String>> getArrivalTimes() {
		return arrivalTimes;
	}
	
	public void addLineName(String lineName) {
		lineNames.add(lineName);
	}
	
	public HashSet<String> getLineNames() {
		return lineNames;
	}
	
	public void addLineStationNode(String lineName, StationNode connectedNode) {
		// if the line doesn't exist in lineStationNodes, add the new line with an empty HashSet
		if (!lineStationNodes.containsKey(lineName)) {
			lineStationNodes.put(lineName, new HashSet<StationNode>());
		}
		// add the connecting StationNode to the HashSet for the line name
		lineStationNodes.get(lineName).add(connectedNode);
	}

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
