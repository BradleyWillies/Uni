/**
 * 
 */
package raffle;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to model a raffle.
 * To facilitate the lucky draw, a raffle uses two separate boxes to
 * keep its prizes and sold tickets.
 * 
 * @author S H S Wong
 * @version 27-09-2020
 */
public class Raffle {

	private String title;
	
	/* !!!! All prizes are kept in a box. 
	 * Define an instance variable for keeping track of the prizes
	 * in this raffle.
	 * # Answered ln 26
	 */ 
	private Box<Prize> prizes;
	
	/* !!!! All sold tickets are kept in a box. 
	 * Define an instance variable for keeping track of 
	 * the tickets that have been sold in this raffle.
	 * # Answered ln 33
	 */ 	
	private Box<Ticket> soldTickets;
	
	public Raffle(String title) { 
		
		this.title = "Raffle for " + title;
		
		/* !!!! Create two box objects for
		 * initialising the instance variables here.
		 * # Answered ln 43 - 44
		 */
		prizes = new Box<Prize>();
		soldTickets = new Box<Ticket>();
	}

	/** 
	 * Returns the title (i.e. purpose) of this raffle.
	 * @return
	 */
	public String title() {
		return title;
	}
	
	/**
	 * Add a prize to the prize box.
	 * @param name
	 * @param value
	 */
	public void addPrize(String name, int value) {
		/* !!!! Complete the implementation for this method
		 * by invoking the method of a Box object.
		 * # Answered ln 65
		 */
		prizes.put(new Prize(name, value));
	}
	
	/**
	 * Sell a ticket to the buyer.
	 * @param buyer	the buyer's name.
	 */
	public void sellTicket(String buyer) {
		/* !!!! Complete the implementation for this method
		 * by invoking the method of a Box object.
		 * # Answered ln 77
		 */
		soldTickets.put(new Ticket(buyer));
	}
	
	/**
	 * Perform the lucky draw.
	 * For each prize in the raffle, 
	 * 	find a winner by drawing a ticket from the box of sold tickets.
	 * If there are more prizes than number of tickets being sold,
	 * 	some prizes will be left behind.
	 *   
	 * @return a mapping between prizes and winning tickets 
	 * 		(each prize is associated with one winning ticket) 
	 */
	public Map<Prize, Ticket> luckyDraw() {
		
		// Creates a mapping of which prize is won by whom.
		Map<Prize, Ticket> winners = new HashMap<Prize, Ticket>();
		
		/* !!!! Make use of an enhanced for statement to allocate
		 * each prize to a winner. Which collection should we iterate
		 * over (prizes or tickets)? Regardless of your answer, the 
		 * iteration will need to end when the tickets in the ticket 
		 * box have been exhausted, i.e. when there are more prizes 
		 * than tickets sold. 
		 * # Answered ln 103 - 107
		 */
		for (Prize prize : prizes) {
			if(!soldTickets.isEmpty()) {
				winners.put(prize, soldTickets.draw());
			}
		}
		
		return winners;
	}

}
