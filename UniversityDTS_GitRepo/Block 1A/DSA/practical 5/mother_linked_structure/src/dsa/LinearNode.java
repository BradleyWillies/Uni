/**
 * To model a node in a linear linked structure (aka linked list).
 * 
 * @author Sylvia Wong
 * @version 18-10-2020
 */
package dsa;

public class LinearNode<E>
{
	/* !!!! Add two fields here.
	 *      A LinearNode object keeps:
	 *      1. an element kept in a linear linked structure, and 
	 *      2. a reference to the LinearNode object which follows 
	 *         this object in the linear linked structure 
	 *         (i.e. the "follower" of this LinearNode object).  
	 */
	
	

	/**
	 * Constructor: Creates an empty linear linked node.
	 */
	public LinearNode()
	{
		/* !!!! Write Java code to initialise the two fields which
		 * 		you defined above.
		 */	
		
		
	}

	/**
	 * Creates a linear linked node storing the specified element.
	 * @param element	the element that is to be kept in this LinearNode object
	 */
	/* !!!! Write Java code for defining a constructor for an LinearNode object.
	 * 		This constructor takes one parameter, which is the element that
	 * 		the newly created LinearNode object should keep. 
	 * 		This LinearNode object will not have a "follower".  
	 */
	public LinearNode (E element)
	{
		
		
	}

	/**
	 * @return the next node, i.e. the node which follows this LinearNode object.
	 */
	public LinearNode<E> getNext()
	{
		return next;
	}

	/**
	 * Sets the next node.
	 * @param node
	 */
	public void setNext (LinearNode<E> node)
	{
		next = node;
	}

	/**
	 * @return the element stored in this LinearNode object.
	 */
	public E getElement()
	{
		return element;
	}

	/**
	 * Sets the element stored in this LinearNode object.
	 * @param element
	 */
	public void setElement (E element)
	{
		this.element = element;
	}
}
