//********************************************************************
//  CircularArrayQueue.java       Authors: Lewis/Chase (Modified by Wong)
//
//  Represents an array implementation of a queue in which the
//  indexes for the front and rear of the queue circle back to 0
//  when they reach the end of the array.
//********************************************************************
package dsaj;

/**
 * Represents an array implementation of a queue in which the
 * indexes for the front and rear of the queue circle back to 0
 * when they reach the end of the array.
 * 
 * @author Lewis/Chase
 * @author S H S Wong
 * @version 16/11/2020
 */
public class CircularArrayQueue<T> implements QueueADT<T>
{
	/* ++++ This implementation does NOT use a field named
	 * 		 "count" to keep track of the number of elements in
	 * 		 the queue.
	 */
	private final int DEFAULT_CAPACITY = 10;
	private int front;
	private int rear;  // the next available cell in the array "queue"
	private T[] contents; 
	
	/******************************************************************
	 Creates an empty queue using the default capacity.
	 ******************************************************************/
	@SuppressWarnings("unchecked")
	public CircularArrayQueue()
	{
		front = 0;
		rear = 0;
		contents = (T[]) (new Object[DEFAULT_CAPACITY]);
	}
	
	/******************************************************************
	 Adds the specified element to the rear of this queue, expanding
	 the capacity of the queue array if necessary.
	 ******************************************************************/
	public void enqueue (T element)
	{
		if (size() == contents.length) 
			expandCapacity();
		
		contents[rear] = element;
		rear = (rear+1) % contents.length;
	}
	
	/******************************************************************
	 Removes the element at the front of this queue and returns a
	 reference to it. Throws an IllegalStateException if the
	 queue is empty.
	 ******************************************************************/
	public T dequeue() throws IllegalStateException
	{
		/* !!!! 
		 * Attempting to remove an element from an empty queue will lead to an error.
		 * In that case, simply throws an appropriate exception.
		 * 
		 * If there is something available for dequeuing, simply:
		 * 1. Get hold of the element to be removed and keep it in safe.
		 * 2. Reset the content of the cell which was occupied by the removed element.
		 * 3. Update the array index kept in "front". 
		 *    (Remember that it is a circular queue, hence "loop-back" may be needed.)
		 * 4. Return the removed element.
		 */
		
		
		
	}
	
	/******************************************************************
	 Returns a reference to the element at the front of this queue.
	 The element is not removed from the queue.  Throws an
	 IllegalStateException if the queue is empty.  
	 ******************************************************************/
	public T first() throws IllegalStateException
	{
		if (isEmpty())
			throw new IllegalStateException("queue is empty");
		
		/* !!!! Return the first element in this queue */
		return contents[front];
	}
	
	/******************************************************************
	 Returns true if this queue is empty and false otherwise. 
	 ******************************************************************/
	public boolean isEmpty()
	{
		/* !!!! A circular queue is empty when the front index is the same as
		 * 		the rear index AND the front of the queue is not occupied by
		 * 		any element.
		 */  
		return (front == rear && contents[front].equals(null));
	}
	
	/******************************************************************
	 Returns the number of elements currently in this queue.
	 ******************************************************************/
	public int size()
	{
		int size = 0;
		
		/* !!!! When front = rear, the queue could be either full or empty.
		 *  
		 * 		To check the size of the queue, we can simply 
		 * 		go through the entire array from index "0" to "contents.length"
		 *      and count all the non-null cells within the array but 
		 *      that is an O(n) operation, which is rather inefficient.
		 *      
		 * 		To improve efficiency, as we have assumed that there is no gap
		 *      between elements within the array queue, we can work out
		 *      the size of the queue by simple arithmetic. 
		 *        
		 *      We can handle the case of an empty queue, a full queue and 
		 *      a not-yet-full queue separately.
		 * 		- If the queue is empty, we simply return 0.      
		 * 		- If the queue is full, we simply return the capacity of the 
		 * 		    underlying circular array.
		 * 		- If the queue is neither empty nor full, we use:
		 * 			 front, rear, contents.length to compute the size of the queue. 
		 * 		
		 * N.B. A method runs in constant time!
		 */
		if (front == rear && isEmpty()) { // queue is empty
			size = 0;
		}
		else if (front == rear && !isEmpty()) { // queue is full
			size = DEFAULT_CAPACITY;
		}
		else {
			size = front > rear ? (contents.length - front + rear + 1) : (rear - front);
		}
		
		return size;
	}
	
	/******************************************************************
	 Returns a string representation of this queue. 
	 ******************************************************************/
	public String toString()
	{
		String result = "";
		
		/* !!!! One way to implement this is to go through the entire array
		 * 		cell by cell.
		 * 		Another way is to, starting from the front of the queue,
		 * 		go through the N cells in the circular array (where N is the size 
		 *      of the queue). At each iteration, we concatenate an element in the queue
		 * 		to our result string. Make sure that the index can "loop back"
		 * 		at the required location.
		 */
		for (int i = 0 ; i < size() ; i++) {
			int index = front + i > contents.length ? (front + i - contents.length) : (front + i);
			result += "Element at index " + index + ": " + contents[index] + "\n";
		}
		
		return result;
	}
	
	/******************************************************************
	 Creates a new array to store the contents of this queue with
	 twice the capacity of the old one.
	 ******************************************************************/
	public void expandCapacity()
	{
		@SuppressWarnings("unchecked")
		T[] larger = (T[])(new Object[contents.length *2]);
		
		int current = front;
		for (int index=0; index < size(); index++)
		{
			larger[index] = contents[current];
			current = (current + 1) % contents.length;
		}
		
		rear = size();
		front = 0;
		contents = larger;
	}
	
	/**
	 * A Main for quick testing
	 * @param args
	 */
	public static void main(String[] args)
	{
		QueueADT<Character> q = new CircularArrayQueue<Character>();
		// Enqueue 9 elements
		for (int i = 0; i < 9; i++)
		{
			q.enqueue((char) (i+97));
			System.out.println("Enqueued: " + (char) (i+97));
		}
		System.out.println("Size: " + q.size());
		System.out.println("Dequeued: " + q.dequeue());
		System.out.println("Dequeued: " + q.dequeue());
		System.out.println("The queue is empty: " + q.isEmpty());
		for (int i = 0; i < 4; i++)
		{
			q.enqueue((char) (i+65));
			System.out.println("Enqueued: " + (char) (i+65));
			System.out.println("Size: " + q.size());		  
		}	
		System.out.println("All elements in the queue: " + q.toString());
		System.out.println("Draining the queue...");
		while (!q.isEmpty())
		{
			q.dequeue();
		}
		System.out.println("The queue is empty: " + q.isEmpty());
	}
}
