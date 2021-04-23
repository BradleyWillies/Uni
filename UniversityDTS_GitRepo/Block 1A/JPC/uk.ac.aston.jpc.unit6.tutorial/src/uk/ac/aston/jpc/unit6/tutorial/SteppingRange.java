package uk.ac.aston.jpc.unit6.tutorial;

import java.util.Iterator;

public class SteppingRange implements SizedIterable<Integer> {
	
	private int minVal;
	private int step;
	private int maxVal;
	
	private SteppingRange(int minVal, int step, int maxVal) {
		this.minVal = minVal;
		this.step = step;
		this.maxVal = maxVal;
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new SteppingRangeIterator(minVal, step, maxVal);
	}
	
	public static SizedIterable<Integer> range(int minVal, int step, int maxVal) {
		return new SteppingRange(minVal, step, maxVal);
	}
	
}
