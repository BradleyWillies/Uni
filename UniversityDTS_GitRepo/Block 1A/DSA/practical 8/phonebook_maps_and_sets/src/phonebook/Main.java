package phonebook;
/**
 * 
 */

/**
 * For starting up a phone book application for maintaining correspondence records.
 * 
 * @author Sylvia Wong
 * @version 13 Sep 2020
 */
public class Main {

	/**
	 * The main for starting up this application.
	 */
	public static void main(String[] args) {
		new GUI(new PhoneBook<String,Correspondence,String>());
	}
}
