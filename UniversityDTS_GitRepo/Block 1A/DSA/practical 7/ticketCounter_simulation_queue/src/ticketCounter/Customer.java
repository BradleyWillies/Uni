package ticketCounter;
//********************************************************************
//  Customer.java       Authors: Lewis/Chase (Modified by Wong)
//
//  Represents a waiting customer.
//********************************************************************

public class Customer
{
	// The arrival time of a customer at the ticket counter queue
	private int arrivalTime;
	// The departure time of a customer from the ticket counter
	private int departureTime;
	
	/******************************************************************
	 Creates a new customer with the specified arrival time.
	 ******************************************************************/
	public Customer (int arrives)
	{
		arrivalTime = arrives;
		/* When the customer arrive, 
		 * 	we don't yet know when they are going to leave.
		 */
		departureTime = 0;
	}
	
	/******************************************************************
	 Returns the arrival time of this customer.
	 ******************************************************************/
	public int getArrivalTime()
	{
		return arrivalTime;
	}
	
	/******************************************************************
	 Sets the departure time for this customer.
	 ******************************************************************/
	public void setDepartureTime (int departs)
	{
		departureTime = departs;
	}
	
	/******************************************************************
	 Returns the departure time of this customer.
	 ******************************************************************/
	public int getDepartureTime()
	{
		return departureTime;
	}
	
	/******************************************************************
	 Computes and returns the total time spent by this customer.
	 ******************************************************************/
	public int totalTime()
	{
		return departureTime - arrivalTime;
	}
}
