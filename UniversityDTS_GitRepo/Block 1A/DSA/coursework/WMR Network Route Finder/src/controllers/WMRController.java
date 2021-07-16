package controllers;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import stack.StackADT;
import wmr.Connection;
import wmr.Line;
import wmr.RouteFinder;
import wmr.Station;
import wmr.StationNode;
import wmr.SubLine;
import wmr.WMRlinesFileLine;

/**
 * A WMRController reads the WMR data files and constructs the WMR system in the appropriate data types.
 * It also implements the methods from the Controller interface.
 * 
 * @author Bradley Willies
 * @version 27/06/2021
 */
public class WMRController implements Controller {

	// maps the name of a station to the station object for all the stations in WMR
	private HashMap<String, Station> stations = new HashMap<String, Station>();
	
	// a set of all station names which have SFA
	private HashSet<String> sfaStationNames = new HashSet<String>();
	
	// maps the name of each line to the line object for all lines in WMR
	private HashMap<String, Line> lines = new HashMap<String, Line>();
	
	// The ordered list of all the lines in the WMRlines.csv file, excluding the header line
	private ArrayList<WMRlinesFileLine> wmrLinesFileLines = new ArrayList<WMRlinesFileLine>();
	
	// maps the name of a train line to a linked list of the connections in that line
	private HashMap<String, LinkedList<Connection>> lineConnections = new HashMap<String, LinkedList<Connection>>();
	
	// maps each train line name to a character starting at a, progressing 1 character at a time
	private HashMap<Character, String> lineNameCharacters = new HashMap<Character, String>();
	
	// linkedHashSet which contains all the nodes (stations) on the WMR network in a graph format
	private LinkedHashSet<StationNode> stationNodes = new LinkedHashSet<StationNode>((int) (stations.size() * 1.5));
	
	// constants to hold the opening time and closing time for the WMR
	private final String OPEN_TIME = "05:00";
	private final String CLOSE_TIME = "23:00";

	/**
	 * Constructor which calls private methods to create the WMR data model
	 */
	public WMRController() {
		readWMRLines();
		createWMRDataSets();
		readSFAFile();
		updateStationsWithSFA();
		createStationNodes();
	}
	
	/**
	 * Method which reads the WMRlines.csv file and adds the data to the wmrLinesFileLines ArrayList in a more usable format
	 */
	private void readWMRLines() {
		try {
			// read the WMRlines data file
			Files.readAllLines(Paths.get("WMRData/WMRlines.csv"), StandardCharsets.US_ASCII).listIterator().forEachRemaining(
				s -> {
					// ignore the header row in the data file
					if (!s.contains("TRAIN LINE,FROM/TO STATION,TO/FROM STATION,TRAVEL TIME (MINS)")) {	
						String[] fileLineTokens = s.split(",");
						fileLineTokens[0] = fileLineTokens[0].replaceAll("\"", "");
						WMRlinesFileLine fileLine = new WMRlinesFileLine(
							fileLineTokens[0].trim(),
							fileLineTokens[1].trim(),
							fileLineTokens[2].trim(),
							Integer.parseInt(fileLineTokens[3])
						);
						wmrLinesFileLines.add(fileLine);
					}
				}
			);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Method to go through the ArrayList of WMR data and create the appropriate data sets from it
	 */
	private void createWMRDataSets() {
		// variables which will be used and updated within the for loop
		char lineNameCharacter = 'a';
		
		// for loop to go through each element in the wmrLinesFileLines ArrayList
		for (int i = 0 ; i < wmrLinesFileLines.size() ; i++) {
			// current line variables
			String trainLineName = wmrLinesFileLines.get(i).trainLineName;
			String fromStation = wmrLinesFileLines.get(i).fromStation;
			String toStation = wmrLinesFileLines.get(i).toStation;
			int travelTime = wmrLinesFileLines.get(i).travelTime;
			
			// create stations if they don't exist and add them 
			if (!stations.containsKey(fromStation)) {
				stations.put(fromStation, new Station(fromStation));
			}
			if (!stations.containsKey(toStation)) {
				stations.put(toStation, new Station(toStation));
			}
			
			// if the trainLineName is not in the lineNameCharacters map, add it and iterate the character variable
			if (!lineNameCharacters.values().contains(trainLineName)) {
				lineNameCharacters.put(lineNameCharacter++, trainLineName);
			}		
			
			// add the stations to the stationsLines HashMap
			
			// create the *connection* that the current file line represents
			Connection connection = new Connection(stations.get(fromStation), stations.get(toStation), travelTime);
			
			// create a new line if the line doesn't exist in lineJourneys then add the connection to its line
			if (!lineConnections.containsKey(trainLineName)) {
				lineConnections.put(trainLineName, new LinkedList<Connection>());
			}
			lineConnections.get(trainLineName).add(connection);
			
			// create *line* if it doesn't exist
			if (!lines.containsKey(trainLineName)) {
				lines.put(trainLineName, new Line(trainLineName));
			}
		}
		
		createSubLines();
	}

	/**
	 * Method to read the WMRstationsWithStepFreeAccess.csv file and add each station name to the sfaStationNames HashSet
	 */
	private void readSFAFile() {
		
		try {
			// read the sfa data file
			Files.readAllLines(Paths.get("WMRData/WMRstationsWithStepFreeAccess.csv"), StandardCharsets.US_ASCII).listIterator().forEachRemaining(
				s -> {
					if (!s.contains("STATION WITH STEP-FREE ACCESS")) {	// ignore the header row in the data file
						sfaStationNames.add(s.trim());	// add the station name to the set of sfa stations, removing any leading or trailing whitespace
					}
				}
			);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Method to update the stepFreeAccess field of all the stations in the sfaStationNames HashSet
	 */
	private void updateStationsWithSFA() {
		for (String stationName : sfaStationNames) {
			if (stations.containsKey(stationName)) {
				stations.get(stationName).setSFA(true);
			}
		}
	}
	
	/**
	 * Method to create the SubLines by going through the connections for each line and finding the branch stations
	 */
	private void createSubLines() {
		// go through the keySet in the hashMap of lines to connections
		for (String lineName : lineConnections.keySet()) {
			// the current line name is the current key, so use that to get the current line
			Line currentLine = lines.get(lineName);
			// get the connections for the current line
			LinkedList<Connection> connections = lineConnections.get(lineName);
			// two subLine variables for the two possible sublines in a line
			SubLine subLine = null;
			SubLine subLine2 = null;
						
			// loop through the connections
			for (int i = 0 ; i < connections.size() ; i++) {
				// store the current connection in a variable
				Connection currentConnection = connections.get(i);
				
				// if it is the first connection create the 1st subline and add the 1st terminus
				if (i == 0) {
					subLine = new SubLine();
					subLine.addTerminus(stations.get(currentConnection.getFromToStation().getName()));
				}
				
				// if there is a second subline, add the connection to that, otherwise add the connection to the first subline
				if (subLine2 != null) {
					subLine2.addConnection(currentConnection);
				}
				else {
					subLine.addConnection(currentConnection);
				}
				
				// if it isn't the last connection
				if (i != connections.size() - 1) {
					// if the current to station doesn't equal the next connections from station, this means we have a terminus and a branch
					if (!currentConnection.getToFromStation().equals(connections.get(i + 1).getFromToStation())) {
						// add the 2nd terminus to the first subline
						subLine.addTerminus(stations.get(currentConnection.getToFromStation().getName()));
						// store the name of the branch station in a variable
						String branchStationName = connections.get(i + 1).getFromToStation().getName();
						// create the 2nd subline
						subLine2 = new SubLine();
						// loop through the 1st subline's connections to copy them to the 2nd subline
						for (int j = 0 ; j < subLine.getConnections().size() ; j++) {
							// if it is the first connection we want this to be the 1st terminus of the 2nd subline
							if (j == 0) {
								// add the terminus to the new subline
								subLine2.addTerminus(subLine.getConnections().get(j).getFromToStation());
							}

							// add each connection to the 2nd subline
							subLine2.addConnection(subLine.getConnections().get(j));
							
							// if the current connection's toStation is the branch station, stop copying connections to the 2nd subline
							if (subLine.getConnections().get(j).getToFromStation().getName().equals(branchStationName)) {
								break;
							}
						}
					}
				}
				else { // if it is the last connection
					// add the last terminus to the correct subline and the sublines to the line 
					if (subLine2 != null) {
						// add the final terminus to the 2nd subline, then add that subline to the line
						subLine2.addTerminus(currentConnection.getToFromStation());
						currentLine.addSubline(subLine2);
					}
					else {
						// add the final terminus to the 1st subline
						subLine.addTerminus(currentConnection.getToFromStation());
					}
					// add the 1st subline to the line, this will always be added since there will always be at least 1 subline
					currentLine.addSubline(subLine);
				}
			}
		}
	}
	
	/**
	 * Method to setup the stationNodes LinkedHashSet so that it represents a graph which can be used by the showAccessiblePath method
	 */
	private void createStationNodes() {
		// go through the keySet in the hashMap of lines to connections, this is how we go through each line
		for (String lineName : lineConnections.keySet()) {
			// get the connections for the current line
			LinkedList<Connection> connections = lineConnections.get(lineName);
			
			// variable to hold the accumulated travel time for connections in this line
			int travelTime = 0;
			
			// go through each connection in the line
			for (int i = 0 ; i < connections.size() ; i++) {
				Connection connection = connections.get(i);
				// boolean to flag if the current stations already have a node, and StationNodes to be used for these stations
				boolean alreadyFromNode = false;
				boolean alreadyToNode = false;
				StationNode fromStationNode = null;
				StationNode toStationNode = null;
				
				// loop through the StationNodes that already exist and if any of them have the current station names then change the flag
				for (StationNode existingNode : stationNodes) {
					// checking for the FromToStation in this connection
					if (existingNode.getStation().equals(connection.getFromToStation())) {
						alreadyFromNode = true;
						fromStationNode = existingNode;
					}
					// checking for the ToFromStation in this connection
					else if (existingNode.getStation().equals(connection.getToFromStation())) {
						alreadyToNode = true;
						toStationNode = existingNode;
					}
				}
				
				// if there aren't StationNodes for either stations, create them
				if (!alreadyFromNode) {
					fromStationNode = new StationNode(connection.getFromToStation());
				}
				if (!alreadyToNode) {
					toStationNode = new StationNode(connection.getToFromStation());
				}
				
				// add this line name to the StationNodes
				fromStationNode.addLineName(lineName);
				toStationNode.addLineName(lineName);
				
				// add each StationNode to the other StationNode's collection of lines to StationNodes
				fromStationNode.addLineStationNode(lineName, toStationNode);
				toStationNode.addLineStationNode(lineName, fromStationNode);
				
				// add the times the train will be at this ToFromStation for this line to the toStationNode
				travelTime += connection.getTravelTime();
				toStationNode.addArrivalTimes(travelTime, OPEN_TIME, CLOSE_TIME, lineName);
				// if this is not the last connection and the next connection's fromStation doesn't equal the current connection's toStation
				if (i < connections.size() - 1 && !connections.get(i+1).getFromToStation().equals(connection.getToFromStation())) {
					// store the branch station name
					String branchStationName = connections.get(i+1).getFromToStation().getName();
					// loop backwards through the connections until we reach the branch station, subtracting the travel times for each connection
					for (int j = i ; j >= 0 ; j--) {
						if (connections.get(j).getToFromStation().getName().equals(branchStationName)) {
							break;
						}
						else {
							travelTime -= connections.get(j).getTravelTime();
						}
					}
				}
				
				// add the station nodes to the nodes collection
				stationNodes.add(fromStationNode);
				stationNodes.add(toStationNode);
			}
		}
	}

	/**
	 * Functional Requirement 1
	 * Lists all termini of a specified railway line.
	 * @param line The ID of the required line as shown in the TUI. This is a character from a - m.
	 * @return the name of all stations that are the end point of the specified lines in the network. 
	 */
	@Override
	public String listAllTermini(String line) {
		// Check if the given line is valid
		if (!line.isEmpty()) {
			Character inputLetter = line.charAt(0);
			if (lineNameCharacters.containsKey(inputLetter)) {
				
				//  Get the train line name from the character map
				String trainLineName = lineNameCharacters.get(inputLetter);
				
				// start a string builder containing the beginning of the response
				StringBuilder sb = new StringBuilder(String.format("%nThe termini for the %s line are:%n", trainLineName));
				
				// get the connections for the specified line
				LinkedList<Connection> connections = lineConnections.get(trainLineName);
				
				// go through each journey (this is O(n)) and append the termini to the string builder
				for (int i = 0 ; i < connections.size() ; i++) {
					
					// if it is the first journey in the line then the FromTo Station is a terminus
					if (i == 0) {
						sb.append(String.format("> %s%n", connections.get(i).getFromToStation().getName()));
					}
					
					// if it is the last journey in the line then the ToFrom Station is a terminus
					if (i == connections.size() - 1) {
						sb.append(String.format("> %s%n", connections.get(i).getToFromStation().getName()));
					}
					
					// if the ToFrom Station name doesn't match the next FromTo Station name, then the current ToFrom station is a terminus
					else if (!connections.get(i).getToFromStation().getName().equals(connections.get(i + 1).getFromToStation().getName())) {
						sb.append(String.format("> %s%n", connections.get(i).getToFromStation().getName()));
					}
				}
				
				// return the built-up string containing the termini
				return sb.toString();
			}
		}
		
		// if the line isn't valid return a string saying so
		return "\n> The specified line does not exist";
	}

	/**
	 * Functional Requirement 2
	 * Lists all stations in their respective order along the specified West Midlands Railway line
	 * and the expected cumulative travel time along the stations on the line.
	 * @param line	The ID of the required line as shown in the TUI.
	 * @return	a String representation of all stations and their accumulative travel time in the specified line.
	 */
	@Override
	public String listStationsInLine(String lineChar) {
		// Check if the given line is valid
		if (!lineChar.isEmpty()) {
			Character inputLetter = lineChar.charAt(0);
			if (lineNameCharacters.containsKey(inputLetter)) {
				//  Get the train line name from the character map
				String trainLineName = lineNameCharacters.get(inputLetter);
				
				// start a string builder containing the beginning of the response
				StringBuilder mainSB = new StringBuilder(String.format("%nThe stations and travel times for the %s line are:", trainLineName));
				
				// get the line associated with the line name
				Line line = lines.get(trainLineName);
				
				// go through each subLine in the line, there could be 1 or 2 sublines
				for (SubLine subLine : line.getSublines()) {
					// create a string builder for the connections in this subline
					StringBuilder connectionsSB = new StringBuilder();
					
					// hold a variable to count the total travel time
					int totalTime = 0;
					
					// go through each connection in the subLine
					for (int i = 0 ; i < subLine.getConnections().size() ; i++) {
						Connection connection = subLine.getConnections().get(i);
						// append the from station and travel time to the string builder
						connectionsSB.append(String.format("%s <%d> ", connection.getFromToStation().getName(), connection.getTravelTime()));
						
						// add to the total time
						totalTime += connection.getTravelTime();
						
						if (i + 1 == subLine.getConnections().size()) {
							connectionsSB.append(connection.getToFromStation().getName());
						}
					}
					
					// append the subline termini and total travel time to the main string builder
					mainSB.append(String.format("%n%s -- %s (%d mins):%n", subLine.getTermini()[0].getName(), subLine.getTermini()[1].getName(), totalTime));
					mainSB.append(connectionsSB.toString());
				}
				
				// return the string from string builder
				return mainSB.toString();
			}
		}
		
		// if the line isn't valid return a string saying so
		return "\n> The specified line does not exist";
	}

	/**
	 * Functional Requirement 3
	 * Show an accessible path between the specified stations, and 
	 * the boarding time of the first train along the identified route.
	 * The path is represented as a sequence of the name of the stations between the specified stations. 
	 * @param plannedStartTime	the planned start time of the journey, should be >= the OPEN_TIME and <= the CLOSE_TIME
	 * @param stationA	the name of the start station
	 * @param stationB	the name of the destination station
	 * @return	a String representation of a path between the specified stations and the expected travel time.
	 */
	@Override
	public String showAccessiblePath(String plannedStartTime, String stationA, String stationB) {
		// check the plannedStartTime is okay
		try {
			LocalTime startTime = LocalTime.parse(plannedStartTime);
			if (startTime.isBefore(LocalTime.parse(OPEN_TIME)) || startTime.isAfter(LocalTime.parse(CLOSE_TIME))) {
				throw new DateTimeException(null);
			}
		}
		catch (Exception e) {
			return "\n> Invalid planned start time; use format \"HH:MM\", trains run from " + OPEN_TIME + " to " + CLOSE_TIME;
		}
		// check that stationA is a real station
		if (!stations.containsKey(stationA)) {
			return "\n> " + stationA + " is not a station on the WMR network";
		}
		// check that stationB is a real station
		if (!stations.containsKey(stationB)) {
			return "\n> " + stationB + " is not a station on the WMR network";
		}
		// check that both stationA and stationB aren't the same
		if (stationA.equals(stationB)) {
			return "\n> You have entered " + stationA + " for both start station and end station; please enter different stations";
		}
		
		// create a RouteFinder object with the graph of stationNodes
		RouteFinder routeFinder = new RouteFinder(stationNodes);
		
		// use the routeFinder to return a stack of the sequence of nodes from stationA to stationB
		StackADT<StationNode> route = routeFinder.findRoute(stationA, stationB);
		
		// if the stack returned is null, return a message saying that there is no route between the given stations
		if (route == null) {
			return "\n> There is no route for a wheelchair user between " + stationA + " and " + stationB;
		}
		else {
			// go through the stack and create a string of the station route as well as the starting time
			StringBuilder returnSB = new StringBuilder("\n> First train arrives at ");
			
			// a variable to hold the arrival time
			String foundArrivalTime = null;
			
			// find the time closest to the planned start time for the first node in the route
			for (ArrayList<String> lineArrivalTimes : route.peek().getArrivalTimes().values()) {
				for (String arrivalTime : lineArrivalTimes) {
					// if any of the first station's arrival times are the same as the plannedStartTime or greater than it
					if (LocalTime.parse(arrivalTime).equals(LocalTime.parse(plannedStartTime)) || LocalTime.parse(arrivalTime).isAfter(LocalTime.parse(plannedStartTime))) {
						// if the foundArrivalTime is null, assign the arrivalTime to it
						if (foundArrivalTime == null) {
							foundArrivalTime = arrivalTime;
						}
						// else if the arrival time is less than the foundArrivalTime, assign arrivalTime to it
						else if (LocalTime.parse(arrivalTime).isBefore(LocalTime.parse(foundArrivalTime))) {
							foundArrivalTime = arrivalTime;
						}
					}
				}
			}
			
			// if the arrival time wasn't found, return a message saying such
			if (foundArrivalTime == null) {
				return String.format("%n> No train's leave from %s on or after planned start time %s", stationA, plannedStartTime);
			}
			// otherwise append the arrival time and go through the stack, appending station names for the text-based route
			else {
				returnSB.append(foundArrivalTime + "\n");
				
				while (!route.isEmpty()) {
					returnSB.append(route.pop().getStation().getName());
					
					// if it isn't the last node in the stack, append an arrow to the String Builder
					if (route.size() > 0) {
						returnSB.append(" -> ");
					}
				}
			}
			
			// return the completed string of stations in the stack
			return returnSB.toString();
		}
	}
	
}
