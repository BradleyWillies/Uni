package wmr;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

import stack.LinkedStack;
import stack.StackADT;

/**
 * RouteFinder can be used to locate a route from a start station to a end station 
 * on a graph of linked stations and lines.
 * 
 * @author Bradley Willies
 * @version 27/06/2021
 */
public class RouteFinder {

	// the graph of stations and lines in WMR network
	private LinkedHashSet<StationNode> wmrGraph;
	
	// a stack to keep track of the nodes along the route
	private StackADT<StationNode> route;
	
	/**
	 * Constructor:
	 * creates a RouteFinder object and initialises the graph and the stack
	 * 
	 * @param wmrGraph
	 */
	public RouteFinder(LinkedHashSet<StationNode> wmrGraph) {
		this.wmrGraph = wmrGraph;
		this.route = new LinkedStack<StationNode>();
	}
	
	/**
	 * Attempts to find a route from the start station to the end station along the graph
	 * of stations and lines.
	 * 
	 * @param startStation
	 * @param endStation
	 * @return a stack containing a sequence of nodes which start at the start station and finish
	 * at the end station, or null if a route can't be found.
	 */
	public StackADT<StationNode> findRoute(String startStation, String endStation) {
		// must reset all station nodes to say they haven't been visited for the next findRoute call
		// also get the starting station node
		StationNode startNode = null;
		for (StationNode node : wmrGraph) {
			node.resetVisited();
			if (node.getStation().getName().equals(startStation)) {
				startNode = node;
			}
		}
		
		// boolean to keep track of if the end station has been reached
		boolean reachedEnd = false;
		
		// record the first station node in the stack
		route.push(startNode);
		
		// mark the start station as visited
		startNode.markVisited();
		
		// go through the graph of nodes to find the route to the endStation
		while (!reachedEnd && !route.isEmpty()) {
			// look at the top node in the stack
			StationNode currentNode = route.peek();
			
			// if the end station has been found and it is SFA
			if (currentNode.getStation().getName().equals(endStation) && currentNode.getStation().getSFA()) {
				reachedEnd = true;
			}
			// if the end station has been found but it isn't SFA
			else if (currentNode.getStation().getName().equals(endStation) && !currentNode.getStation().getSFA()) {
				// use the helper method to try and find an SFA station nearby
				StationNode endNode = getClosestSFAStation(currentNode);
				
				// if there isn't a nearby station endNode will be null, break from the while loop with reachedEnd still false
				if (endNode == null) {
					break;
				}
				// otherwise add the end node to the route and mark reachedEnd as true
				else {
					route.push(endNode);
					reachedEnd = true;
				}
			}
			// if the end station hasn't been found
			else {
				// use the helper method to get the next valid move in the graph from the current node
				StationNode nextMove = getNextStationNode(currentNode);
				
				// if the nextMove is null, pop one node from the stack
				if (nextMove == null) {
					route.pop();
				}
				// otherwise add the new node to the route and mark it as visited
				else {
					route.push(nextMove);
					nextMove.markVisited();
				}
			}
		}
		
		// if the end is reached return the reverse of the route stack so that it is in start to finish order
		if (reachedEnd) {
			return reverseStack();
		}
		// otherwise the end couldn't be reached so return null
		else {
			return null;
		}
	}
	
	/**
	 * Helper method to try and find a nearby SFA station to the one provided
	 * @param currentNode the node to search from
	 * @return an SFA stationNode nearby to the current node, or null if none could be found
	 */
	private StationNode getClosestSFAStation(StationNode currentNode) {
		// create the station node to be returned
		StationNode nextNode = null;
		
		// get all the linked stationNodes to the current node
		HashMap<String, HashSet<StationNode>> lineStationNodes = currentNode.getLineStationNodes();
		
		// go through the current node's connected nodes
		for (HashSet<StationNode> nodeSet : lineStationNodes.values()) {
			for (StationNode linkedNode : nodeSet) {
				// if the linked node's station is SFA, assign this to next node and break from the loop
				if (linkedNode.getStation().getSFA()) {
					nextNode = linkedNode;
					break;
				}
				// otherwise look at the nodes linked to the linked node and check if any of those stations have SFA
				else {
					// get the nodes connected to the linked node
					Collection<HashSet<StationNode>> nextLinkedNodeSets = linkedNode.getLineStationNodes().values();
					
					// go through the linked nodes to check if any of these are SFA
					for (HashSet<StationNode> nextNodeSet : nextLinkedNodeSets) {
						for (StationNode nextLinkedNode : nextNodeSet) {
							// if the next linked node's station is SFA, assign this to next node and break from the loop
							if (nextLinkedNode.getStation().getSFA()) {
								nextNode = nextLinkedNode;
								break;
							}
						}
					}
				}
			}
			// if nextNode has been assigned a node, break from the loop
			if (nextNode != null) {
				break;
			}
		}
		
		// return either the next node or null
		return nextNode;
	}
	
	/**
	 * Attempts to get a StationNode connected to the provided currentNode on the WMR graph 
	 * which hasn't already been visited, or returns null if they have all been visited.
	 * 
	 * @param currentNode
	 * @return the next StationNode connected to the currentNode which hasn't been visited 
	 * already, or null if there are no more nodes to visit
	 */
	private StationNode getNextStationNode(StationNode currentNode) {
		// create the node to return
		StationNode nextNode = null;
		
		// get all the linked stationNodes to the current node
		HashMap<String, HashSet<StationNode>> lineStationNodes = currentNode.getLineStationNodes();
		
		// go through the current node's connected nodes
		for (HashSet<StationNode> nodeSet : lineStationNodes.values()) {
			for (StationNode linkedNode : nodeSet) {
				// if the linked node isn't visited set it to the next node and break the loop
				if (!linkedNode.isVisited()) {
					nextNode = linkedNode;
					break;
				}
			}
			// if the next node has been found break the loop
			if (nextNode != null) {
				break;
			}
		}
		
		// return either the next node or null
		return nextNode;
	}
	
	/**
	 * returns a stack of stationNodes which represent a route from a start station to
	 * an end station along the WMR network
	 * 
	 * @return a stack of StationNodes with the top of the stack being the starting node
	 */
	private StackADT<StationNode> reverseStack() {
		// create a stack to hold the reversed route
		StackADT<StationNode> reversedRoute = new LinkedStack<StationNode>();
		
		// go through the main stack, popping each element and putting it into the reversed stack
		while (!route.isEmpty()) {
			reversedRoute.push(route.pop());
		}
		
		// return the reversed stack
		return reversedRoute;
	}
}
