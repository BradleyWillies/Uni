package dsa;

import java.util.Iterator;

/**
 * An array implementation of an iterator over a collection.
 * 
 * @author Chase/Lewis
 * @author Alan Barnes
 * @author S H S Wong
 * 
 * @version 10-10-2020
 * 
 * @param <T>
 */
public class ArrayIterator<T> implements Iterator<T> {
	private T[] contents;  // the elements to be iterated over
	// the index of the element that is to be returned in the next() operation
	private int cursor;   
	private int limit;

	/** 
	 * Constructor: constructs an iterator (which is backed by an array)
	 *  for iterating over the elements in the specified array.
	 *  
	 * As the specified array may contain null elements at the end, 
	 * variable "size" specifies the number of elements that are to
	 * be iterated over the specified array.
	 * 
	 * @param array
	 * @param size
	 */
	public ArrayIterator(T[] array, int size) {
		contents = array;
		limit = size;
		cursor = 0;
	}

	@Override
	public boolean hasNext() {
		return cursor < limit;
	}
	
	@Override
	public T next() {
		if (!hasNext()) {
			throw new IllegalStateException("ArrayIterator: no next element");
		} else {
			return contents[cursor++]; // The actual value being returned is contents[cursor].
										// The increment is done after establishing that 
										// contents[cursor] as the return value.
		}
	}
}
