/**
 * 
 */
package ancestry;

/**
 * To model a female person
 * 
 * @author Sylvia Wong
 * @version 18-10-2020
 */
public class Female {

	private String name;
	
	/**
	 * Constructor
	 */
	public Female(String name) 
	{
		this.name = name;
	}
	
	/**
	 * @return a string representation of this female object
	 */
	@Override
	public String toString()
	{
		return name;
	}

}
