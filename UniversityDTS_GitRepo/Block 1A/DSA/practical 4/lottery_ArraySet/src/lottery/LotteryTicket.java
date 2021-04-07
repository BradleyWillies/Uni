/**
 * 
 */
package lottery;

import dsa.ArraySet;
import dsa.SetADT;

/**
 * A lottery ticket contains 6 unique numbers. 
 * There are two ways to create a lottery ticket:
 * - create a ticket with 6 random numbers within the expected range
 * - create a ticket with 6 client-specified numbers
 * To allow checking of winning status, a lottery ticket needs to 
 * reveal its containing set of numbers.
 * 
 * @author Sylvia Wong
 * @version	10/10/2020
 */
public class LotteryTicket {

	private int id;           // The ID for this lottery ticket.
	
	/* The set of lottery numbers that the buyer of this ticket thinks 
	 * would be picked in the coming lottery session.
	 */
	/* ++++ We CANNOT simply write:
	 *         	private SetADT<int> numbers;
	 *      because a type variable CANNOT be used for keeping a 
	 *      primitive type (e.g. int, char, boolean, etc). 
	 *      Type variables can ONLY be used for keeping reference types
	 *      (i.e. name of classes like String, Object, Seminar, etc.).
	 */
	private SetADT<Integer> chosenNumbers;
		

	/**
	 * Constructor: Create a lottery ticket with 6 randomly-generated numbers.
	 * @param id   The id for this lottery ticket.
	 * @param lottery  The lottery with which this lottery ticket is associated.
	 */
	public LotteryTicket(int id, Lottery lottery)
	{
		this.id = id;
		chosenNumbers = new ArraySet<>(lottery.maxPick());
		
		java.util.Random random = new java.util.Random();
		
		/* !!!! Write a loop to add lottery.maxPick() unique numbers
		 * 		to this lottery ticket. 
		 *      These numbers must be within the range of available 
		 *      lottery numbers.  
		 */ 
		// fill our set with lottery.maxPick() elements
		
		
	}
	
	/**
	 * Create a lottery ticket with 6 specified numbers. 
	 * @param numbers	the lottery numbers that this ticket contains 
	 */
	public LotteryTicket(int id, Lottery lottery, SetADT<Integer> numbers)
	{
		this.id = id;
		chosenNumbers = new ArraySet<>(lottery.maxPick());
		
		/* !!!! Need to add a statement here to add the given set
		 * 		of numbers to this lottery ticket.
		 */
		// Add all numbers to the (currently empty) set of chosen numbers. 
		
	}
	
	/**
	 * Returns the ticket number
	 * @return	the number of this ticket
	 */
	public int id() {
		return id;
	}
	
	/**
	 * Return the chosen numbers specified in this ticket.
	 * @return	the set of numbers that are on this ticket
	 */
	public SetADT<Integer> chosenNumbers()
	{
		return chosenNumbers;
	}
	
	/**
	 * Return a string version of this lottery ticket
	 * @return	String
	 */
	public String toString()
	{
		return "Ticket no.: " + id + '\n' + 
			"Chosen numbers: " + '\n' + chosenNumbers.toString();
	}
	
	/**
	 * The Main: for quick testing 
	 * @param args
	 */
	public static void main(String[] args) {
		Lottery lotto = new Lottery(7,6);
		LotteryTicket t1 = new LotteryTicket(1, lotto);
		SetADT<Integer> numbers = new ArraySet<Integer>(6);
		for(int i = 0; i < lotto.maxPick(); i++)
			numbers.add(i);
		LotteryTicket t2 = new LotteryTicket(2, lotto, numbers);
		System.out.println(t1.toString());
		System.out.println(t2.toString());
	}


}
