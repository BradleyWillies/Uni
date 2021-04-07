/**
 * 
 */
package lottery;

/**
 * The top-level class of this lottery application. 
 * Invoke this to start up the application.
 * 
 * @author Sylvia Wong
 * @version	10/10/2020
 */
public class LotteryApp {

	/**
	 * The main for starting up this lottery application.
	 * @param args	input argument(s)
	 */
	public static void main(String[] args) {
		int largest = 7;   // can also be 14, 49, 59, etc
		int maxPick = 6;   // number of numbers to be pick in a lottery draw
		
		new GUILottery(new Lottery(largest, maxPick));
	}
}
