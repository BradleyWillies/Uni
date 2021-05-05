package uk.ac.aston.jpc.nested;

public interface SizedIterable<T> extends Iterable<T> {

	@SuppressWarnings("unused")
	default int size() {
		int count = 0;
		for (Object o : this) {
			count++;
		}
		return count;
	}

}
