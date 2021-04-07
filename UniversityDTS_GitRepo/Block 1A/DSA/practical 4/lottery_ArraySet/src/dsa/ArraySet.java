package dsa;

import java.util.Iterator;
import java.util.Random;

/**
 * An array implementation of a bounded set.
 * 
 * @author Alan Barnes and S H S Wong
 * @version 10-10-2020
 *
 * @param <T>
 */
public class ArraySet<T> extends AbstractSet<T> {

	private static final int DEFAULT_CAPACITY = 100;

	private T[] contents;		// the elements kept in this set
	private int currentSize;

	/** 
	 * Constructor: construct a new empty set with specified capacity
	 * 
	 * @param maxSize
	 */
	@SuppressWarnings("unchecked")
	public ArraySet(int maxSize) {
		/* ++++ Question:
		 * 
		 * Can we simply replace:
		 * 		contents = (T[])(new Object[DEFAULT_CAPACITY]);
		 * by:  
		 *        contents = new T[DEFAULT_CAPACITY];
		 *         
		 * Answer: No, because we can't create a generic array.
		 */
		contents = (T[]) (new Object[maxSize]);
		currentSize = 0;
	}

	/**
	 * Constructor: construct a new empty set of default capacity
	 */
	public ArraySet() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Copy Constructor: Creates a new ArraySet object from the given ArraySet object. 
	 * @param set	the given ArraySet object which is to be used to create the new set
	 */
	public ArraySet(ArraySet<T> set)
	{
		/* !!!! Initialise the fields "currentSize" and "contents" appropriately.
		 * "currentSize" must have the same value as that in the given set.
		 * "content" must hold the same elements as that in the given set.  
		 * 
		 * N.B.: Arrays can be cloned effectively using their clone() method. 
		 */
		
		
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public boolean isEmpty() {
		return (currentSize == 0);
	}

	@Override
	public boolean contains(T element) {
		for (int i = 0; i < currentSize; i++) {
			if (element.equals(contents[i]))
				return true;
		}
		return false;
	}

	/*
	 * Creates a new array to store the contents of the set with
	 * twice the capacity of the old one.
	 */
	@SuppressWarnings("unchecked")
	private void expandCapacity()
	{
		/* !!!! Create a new, larger Object array that can hold twice as many elements
		 * 		as the old one and cast this Object array to a T array, 
		 * 		where T is a type variable.
		 * 
		 *      Copy elements in the old array to the new one one by one.
		 *      
		 *      Update the contents of this set using the new array.
		 */
		
		
		
		
	}

	/* Adds an element to this set */
	@Override
	public boolean add(T element) {
		if (!contains(element)) {
			if (size() == contents.length) {
				expandCapacity();
			}
			contents[currentSize] = element;
			currentSize++;
			return true;
		} else {
			return false; // element already present -- array unchanged
		}
	}

	/* Removes an element from this set */
	@Override
	public boolean remove(T element) {
		for (int i = 0; i < currentSize; i++) {
			if (element.equals(contents[i])) {
				/* Element found at position i, so decrease size 
				 * and move the last element in the array to position i
				 * to replace it
				 */
				currentSize--;
				contents[i] = contents[currentSize];
				return true;
			}
		}
		return false; // element not present -- array unchanged
	}

	/**
	 * Perform intersection on this set and the given set, i.e.:
	 *  return a set containing those elements that exist in BOTH sets.
	 * Return the results of the intersection in a new set.
	 * @param anotherSet	The given set. 
	 * 			(The given set does not need to be implemented as 
	 * 			 an ArraySet. So long as it is some kind of set
	 *             object that conforms the specification of the
	 *             SetADT<T> interface, we can use it.)   
	 * @return the results of set intersection
	 */
	@Override
	public SetADT<T> intersection(SetADT<T> anotherSet)
	{
		/* !!!! Create a new SetADT<T> object for keeping the results 
		 *      of our set intersection operation.
		 */
		

		/* !!!! Write Java code to iterate over either:
		 * 		- the given set 
		 * 		  (i.e. the set object stored in the parameter "anotherSet"),
		 * 		  or
		 *		- this ArraySet object (i.e. "this")
		 *      element by element and check if each element is contained
		 *      within this set (i.e. the elements that are stored
		 *      in the field "contents"). 
		 *      If so, add the element to our new set.
		 *      
		 *      To improve efficiency, we iterate over the smaller set.
		 */
		

		/* !!!! Return the results of this intersection operation. 
		 */
		
	}

	/* !!!! Write the method difference(SetADT<T>), which returns a new
	 * 		ArraySet<T> object containing the result of performing 
	 *      set difference between this set and the given set (i.e. "set").
	 */


	/**
	 * Returns a new set that is the union of this set and the parameter.
	 * @param set	the given set
	 * @return a new SetADT object
	 */
	@Override
	public SetADT<T> union (SetADT<T> set)
	{
		/* ++++
		 * Set union means creating a new set from two given sets.
		 * In our case, the two given sets are this set (i.e. "this") 
		 * and the given set (i.e. "set"). 
		 * We need to create a new set for storing the result of the
		 * set union. The new set can be created using the copy constructor
		 * so that it is initialised using this set (i.e. "this") upon
		 * creation.
		 *  
		 * As the add methods in this class ensure uniqueness before 
		 * adding an element, we can simply invoke the addAll method
		 * to add elements from "set" to the newly created set. 
		 */

		/* !!!!
		 * Use the copy constructor to define and initialise 
		 * a new SetADT<T> variable for storing the union
		 * of this set and the given set. 
		 */
		

		/* !!!! Add all elements in this set to the newly created ArraySet.
		 */
		

		/* !!!! Return the results of this union operation. 
		 */
		
	}

	public Iterator<T> iterator() {
		return new ArrayIterator<>(contents, currentSize);
	}

	/**
	 * Removes a random element from the set.
	 * @return	a random element in the set
	 */
	@Override
	public T removeRandom() {
		Random randomiser = new Random();
		// Generate a random number corresponds to the index of the element 
		// that is to be removed from the set.
		int toRemoveAt = randomiser.nextInt(currentSize);
		// Keep the element in a temp variable
		T result = contents[toRemoveAt];
		currentSize--;
		// Move the last element in the array set to fill the gap
		contents[toRemoveAt] = contents[currentSize];
		// Return the chosen element
		return result;
	}

	/* 	
	 * Returns a string representation of this set.
	 * Each element within the set is placed in a new line.
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = "";

		for (int index=0; index < currentSize; index++) {
			// concatenate the array elements one by one
			result = result + contents[index].toString() + "\n";
		}

		return result;
	}

	/** 
	 * The main: 
	 * a quick testing for the new set intersection operation only.
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{
		SetADT<Integer> s1 = new ArraySet<>(6);
		for(int i=0; i<6; i++)
			s1.add(i);
		SetADT<Integer> s2 = new ArraySet<>(6);
		for(int i=2; i<8; i++)
			s2.add(i);

		/* !!!! Don't forget to uncomment the following line BEFORE
		 *      you run the test.
		 */
//		System.out.println(s1.intersection(s2));		 
	}

}