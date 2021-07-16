package stack;

/**
 * LinearNode.java  
 * Represents a node in a linked list.
 * 
 * @author Lewis/Chase & S H S Wong
 * @version 22-10-2020
 * 
 * @param <T>
 */
public class LinearNode<T> {
	private T element;
	private LinearNode<T> next;

	/**
	 * Constructor: Creates an empty node.
	 */
	public LinearNode() {
		next = null;
		element = null;
	}

	/**
	 * Constructor: Creates a node storing the specified element.
	 * @param elem	The specified element that is to be kept by this LinearNode.
	 */
	public LinearNode(T elem) {
		next = null;
		element = elem;
	}

	/**
	 *  Returns the node that follows this one.
	 * @return	The node that follows this one.
	 */
	public LinearNode<T> getNext() {
		return next;
	}

	/**
	 *  Sets the node that follows this one.
	 * @param node	The node which is to follow this one.
	 */
	public void setNext(LinearNode<T> node) {
		next = node;
	}

	/**
	 *  Returns the element stored in this node.
	 * @return	The element that is kept within this node.
	 */
	public T getElement() {
		return element;
	}

	/**
	 *  Sets the element stored in this node.
	 * @param elem	The element that is to be kept within this node.
	 */
	public void setElement(T elem) {
		element = elem;
	}
}
