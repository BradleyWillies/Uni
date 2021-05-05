package uk.ac.aston.jpc.nested.original;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SteppingRangeIterator implements Iterator<Integer> {
	private int current;
	private final int step, max;

	public SteppingRangeIterator(int min, int step, int max) {
		this.current = min;
		this.step = step;
		this.max = max;
	}

	@Override
	public boolean hasNext() {
		return current < max;
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
