package uk.ac.aston.jpc.unit6.tutorial;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SteppingRangeIterator implements Iterator<Integer> {

	private int current;
	private final int step, max;
	
	public SteppingRangeIterator(int current, int step, int max) {
		this.current = current;
		this.step = step;
		this.max = max;
	}
	
	public boolean hasNext() {
		return (current < max);
	}
	
	@Override
	public Integer next() {
		if (current >= max) {
			throw new NoSuchElementException();
		}
		final int result = current;
		current += step;
		return result;
	}
	
}
