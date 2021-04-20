package uk.ac.aston.jpc.unit6.tutorial;

public interface SizedIterable<T> extends Iterable<T> {
	default int size() { return 0; }
}
