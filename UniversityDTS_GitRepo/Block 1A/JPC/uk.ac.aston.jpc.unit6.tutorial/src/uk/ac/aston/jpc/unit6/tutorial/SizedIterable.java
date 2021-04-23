package uk.ac.aston.jpc.unit6.tutorial;

public interface SizedIterable<T> extends Iterable<T> {
	
	default int size() { 
		int count = 0;
		for(Object o : this) {
			count++;
		}
		return count; 
	}
}
