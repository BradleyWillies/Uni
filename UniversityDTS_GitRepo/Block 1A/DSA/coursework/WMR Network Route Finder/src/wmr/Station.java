package wmr;

/**
 * A Station represents a single node on the WMR network.
 * It has a name and can have step-free access or not.
 * 
 * @author Bradley Willies
 * @version 27/06/2021
 */
public class Station {
	
	// fields containing the name of this Station and whether it is SFA or not
	private String name;
	private boolean stepFreeAccess;
	
	/**
	 * constructor to create a Station with the provided name and initialises 
	 * stepFreeAccess to false
	 * 
	 * @param name
	 */
	public Station(String name) {
		this.name = name;
		stepFreeAccess = false;
	}
	
	/**
	 * 
	 * @return the name of this Station
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * sets the stepFreeAccess field of this Station to the provided 
	 * boolean value
	 * 
	 * @param stepFreeAccess
	 */
	public void setSFA(boolean stepFreeAccess) {
		this.stepFreeAccess = stepFreeAccess;
	}
	
	/**
	 * 
	 * @return boolean determining whether this Station is SFA or not
	 */
	public boolean getSFA() {
		return stepFreeAccess;
	}

}
