package uk.ac.aston.jpc.unit6.tutorial;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SteppingRangeIterator implements Iterator<Integer> {

	private int minVal;
	private int step;
	private int maxVal;
	
	public SteppingRangeIterator(int minVal, int step, int maxVal) {
		this.minVal = minVal;
		this.step = step;
		this.maxVal = maxVal;
	}
	
	public boolean hasNext() {
		try {
			this.next();
			return true;
		}
		catch(NoSuchValueException NSE) {
			return false;
		}
	}
	
	@Override
	public Integer next() {
		
	}
	
}
