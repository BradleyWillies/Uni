/**
 * 
 */
package raffle;

/**
 * A class to model the information about a prize.
 * 
 * @author S H S Wong
 * @version 27-09-2020
 */
public class Prize {
	
	private String name;
	private int value;
	
	/**
	 * Constructor 
	 * @param name
	 * @param value
	 */
	public Prize(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Returns the name (description) of this prize.
	 * @return
	 */
	public String name() {
		return name;
	}
	
	/**
	 * Returns the value of this prize, 
	 * 	i.e. how much in GBP£ this prize is worth.
	 * @return
	 */
	public int value() {
		return value;
	}
	

	/* !!!! Override the toString method inherited from class Object.
	 * Your implementation should include the name and value of 
	 * the prize in the returning String.
	 * 
	 *  (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */	
	public String toString() {
		return ("The name of the prize is: " + name + "\nThe value of the prize is: " + value);
	}

}
