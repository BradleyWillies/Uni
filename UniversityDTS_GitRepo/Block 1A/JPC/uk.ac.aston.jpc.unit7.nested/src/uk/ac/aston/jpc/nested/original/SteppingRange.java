package uk.ac.aston.jpc.nested.original;

import java.util.Iterator;

import uk.ac.aston.jpc.nested.SizedIterable;

public class SteppingRange implements SizedIterable<Integer> {
	private final int min, step, max;

	public SteppingRange(int min, int step, int max) {
		this.min = min;
		this.step = step;
		this.max = max;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new SteppingRangeIterator(min, step, max);
	}

	public static SizedIterable<Integer> range(int min, int step, int max) {
		return new SteppingRange(min, step, max);
	}
}
