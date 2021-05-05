package uk.ac.aston.jpc.nested.v4;

import java.util.Iterator;
import java.util.NoSuchElementException;

import uk.ac.aston.jpc.nested.SizedIterable;

public class SteppingRange implements SizedIterable<Integer> {
	private int min;
	private final int step, max;
	
	public SteppingRange(int min, int step, int max) {
		this.min = min;
		this.step = step;
		this.max = max;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			private int current = min;
			
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
		};
	}

	public static SizedIterable<Integer> range(int min, int step, int max) {
		return new SteppingRange(min, step, max);
	}
}
