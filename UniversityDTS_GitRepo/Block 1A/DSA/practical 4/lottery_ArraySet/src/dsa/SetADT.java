package dsa;

/**
 * SetADT defines a collection that contains no duplicate elements.
 * 
 * @author Alan Barnes and S H S Wong
 * @version 10-10-2020
 */
/* We make SetADT<T> extend interface Iterable<T>,
 * so we can use enhanced for loops with sets. 
 */
public interface SetADT<T> extends Iterable<T>
{ 
	/** 
	 * Adds the element to the set. 
	 * If the element is already present, the set is not modified. 
	 * Returns true if the set is modified.
	 * 
	 * @return true if the set is modified
	 */
	public boolean add(T element);

	/** 
	 * Adds all elements of set A to this set. 
	 * 
	 * @return true if the set is modified
	 */
	/* !!!! Add the abstract method addAll here.
	 */ 
	

	/** Removes and returns a random element from this set. */
	/* !!!! Add the abstract method removeRandom here.
	 */   
	
	
	/** 
	 * Removes the specified element from the set.
	 *  If the element is not present, the set is not modified.
	 *  Returns true if the set is modified.
	 *  
	 *  @return true if the set is modified
	 */
	public boolean remove(T element);

	/** 
	 * Removes all elements of set A from this set. 
	 * 
	 * @return true if the set is modified.
	 */
	public boolean removeAll(SetADT<T>  A);

	/** 
	 * Removes all the elements from this set except those in set A.
	 * Returns true if the set is modified.
	 * 
	 * @return true if the set is modified
	 */
	public boolean retainAll(SetADT<T>  A);

	/**  
	 * Returns a new set equal to  $(this \cup A)$  
	 * 
	 * @return the results of the set union in a new set object
	 */
	public  SetADT<T>  union(SetADT<T> A);
 
	/**  
	 * Returns a new set equal to  $(this \cap A)$  
	 * 
	 * @return the results of the set intersection in a new set object
	 */
	/* !!!! Add a new abstract method here for specifying the new
	 *      set operation that we need, i.e. set intersection.
	 *      What type of value would the input of this method be?
	 *      What type of value would be returned as a result?
	 *      
	 *      Don't forget to write an appropriate Java doc comment
	 *      for this method.
	 */ 	
	

	/**  
	 * Returns a new set equal to  $(this \setminus A)$ 
	 * 
	 * @return the results of the set difference in a new set object
	 */
	public SetADT<T> difference(SetADT<T> A);

	/**  
	 * Returns true if  $this \subseteq A$  
	 *
	 * @return true if the specified set is a subset of this set object
	 */
	public boolean isSubset(SetADT<T> A);

	/**  
	 * Returns true if this set contains the element.
	 * 
	 * @return true if this set contains the specified element
	 */
	public boolean contains(T element);

	/**  
	 * Returns true if this set and the set A contain 
	 * exactly the same elements.
	 * 
	 * @return true if this set contains the same elements as the specified set
	 */      
	public boolean equals(SetADT<T> A);

	/**  
	 * Returns true if this set contains no elements.
	 * 
	 * @return true if this set contains no elements
	 */
	public boolean isEmpty();

	/**  
	 * Returns the number of elements in this set.
	 * 
	 * @return the number of elements in this set
	 */
	public int size();

}
