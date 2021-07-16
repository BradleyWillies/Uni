package wmr;

/**
 * A Station represents a single node on the WMR network.
 * It has a name and can have step-free access or not.
 * 
 * @author bwillies
 * @version 27/06/2021
 */
public class Station {
	
	private String name;
	private boolean stepFreeAccess;
	
	public Station(String name) {
		this.name = name;
		stepFreeAccess = false;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSFA(boolean stepFreeAccess) {
		this.stepFreeAccess = stepFreeAccess;
	}
	
	public boolean getSFA() {
		return stepFreeAccess;
	}

}
