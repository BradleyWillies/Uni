/**
 * 
 */
package lottery;

import dsa.ArraySet;
import dsa.SetADT;

/**
 * The heart of a lottery application. It represents what the organiser
 * of lottery knows and can do.
 * 
 * A lottery needs to know what tickets have been sold. 
 * To facilitate the drawing of winning numbers, it needs to know the
 * complete set of available numbers. It also needs to keep track of 
 * the current winning numbers so as to check if anybody has won in
 * the current lottery session.  
 * 
 * It sells lottery ticket and runs a lottery draw. 
 * It can find out who has won and 
 * can reset all knowledge so as to prepare for
 * running the next lottery session.
 * 
 * @author Sylvia Wong
 * @version	10/10/2020
 */
public class Lottery {

	/* The upper limit of the specified range. 
	 * A realistic value would be 49, 59, etc.
	 * 
	 * ++++ 
	 * We are keeping this range small so that it is easier for someone
	 * to win. Otherwise, it'd be difficult for us to check if the 
	 * application is working or not.
	 */
	private int largest;        // The largest number that can be picked in this Lottery.
	
	private int maxPick;        // Number of numbered balls to be picked, usually 6.
	
	private SetADT<Integer> availableNumbers;  // e.g. 1 - 59
	private SetADT<LotteryTicket> tickets;
	private SetADT<Integer> winningNumbers;    // e.g. 6
	
	private int numOfTicketsSold;              // the number of lottery tickets sold for this Lottery
	
	/**
	 * Constructor 
	 */
	public Lottery(int largest, int maxPick) {
		this.maxPick = maxPick;
		this.largest = largest;
		/* !!!! Write an assignment statement here for initialising availableNumbers.
		 */
		// create the required numbers for this lottery
		
		
		// prepare this lottery for running the next session
		reset();
	}

	/**
	 * Reset the lottery so as to prepare for running the next session.
	 */
	public void reset()
	{
		// populate the set with all valid lottery numbers
		for (int i=1; i <= largest; i++)
		{
			availableNumbers.add(i);
		}
		/* !!!! Write two assignment statements here for initialising 
		 * 		the instance variables: tickets and winningNumbers.
		 */
		// the current set of lottery tickets should be empty
		
		
		// reset the existing winning numbers
		
		
		numOfTicketsSold = 0;
	}	

	/**
	 * Return the maximum number of numbers in a lottery ticket for this lottery.
	 * @return
	 */
	public int maxPick() 
	{
		return maxPick;
	}
	
	/** 
	 * Return the largest number available in this lottery. 
	 * @return
	 */
	public int largest()
	{
		return largest;
	}
	
	/**
	 * Sell a lucky dip ticket 
	 * @return the ticket number
	 */
	public int sellTicket()
	{
		/* !!!! Create an appropriate LotteryTicket object and update
		 * 		the set of tickets sold so far.
		 */
		
		
		
	}
	
	/**
	 * Sell a standard ticket
	 * (The customer picks their own numbers.) 
	 * @param numbers	The numbers that the customer wants to buy.
	 * @return the ticket number
	 */
	public int sellTicket(SetADT<Integer> numbers)
	{
		/* !!!! Create an appropriate LotteryTicket object and update
		 * 		the set of tickets sold so far.
		 */
		
	}
	
	/**
	 * Return the details of all purchase tickets 
	 * @return 	a string representation of all tickets sold
	 */
	public String allTickets()
	{
		String result = "All Valid Tickets: ";
		if (tickets.isEmpty()) {
			result = "No ticket sold.";
		}
		else {
			result += tickets.toString();
		}
		return result;
	}
	
	/**
	 * Run a lottery by picking 6 random numbers from the available set. 
	 * @return	the 6 winning numbers in an int array
	 */
	public int[] drawNumbers()
	{
		int[] results = new int[maxPick];
		
		/* !!!! Write an iteration here for simulating the drawing
		 * 		of 6 winning numbers for the current lottery session.
		 * 		Don't forget to keep all drawn numbers in the int array 
		 * 		also.
		 */
		
		
		
		return results;
	}
	
	/**
	 * Reveal the winning numbers
	 * @return	a message that states the winning numbers
	 */
	public String winningNumbers()
	{
		return "The winning numbers are: \n" + winningNumbers.toString();
	}
	
	/**
	 * Check who, if any, have won the jackpot. 
	 * @return	A message showing if there is any winner and if so, who.
	 */
	public String hasWon()
	{
		/* !!!! You will need to check through all current lottery tickets 
		 * 			to see if any ticket has won.
		 * 
		 *      How to check if someone has won? There are two ways:
		 *      	(1) We can perform intersection on the two sets of numbers 
		 *      		(i.e. the purchased numbers and the winning numbers).
		 *      	    If the resulting set has MAX_PICK elements, 
		 *         		   the ticket is a winning ticket.
		 *          (2) We can perform equals check on both sets.
		 *          Which algorithm is more efficient?
		 *           
		 *      Don't forget to return a message regarding the result. 
		 */
		String message = "";
		
		
		// If message is empty, that means no-one has won this time.
		if (message.equals("")) {
			message = "No-one has won this lottery session. Roll over jackpot."; 
		}
		else {
			message = "The winner(s) are: " + message;
		}
		
		return message;
	}

	
	/**
	 * The Main: for quick testing.
	 * @param args
	 */
	public static void main(String[] args) {
		int largest = 7;   // can also be 14, 49, 59, etc
		int maxPick = 6;   // number of numbers to be pick in a lottery draw
		Lottery lotto = new Lottery(largest, maxPick);
		final int howMany = 1000;
		System.out.println("Selling " + howMany + " Lucky Dip tickets...");
		for (int i = 0; i < howMany; i++)
			lotto.sellTicket();
		System.out.println("Run the lottery...");
		lotto.drawNumbers();
		System.out.println(lotto.winningNumbers());
		System.out.println(lotto.hasWon());
		System.out.println("Showing all purchases ticket...");
		System.out.println(lotto.allTickets());
	}

}
