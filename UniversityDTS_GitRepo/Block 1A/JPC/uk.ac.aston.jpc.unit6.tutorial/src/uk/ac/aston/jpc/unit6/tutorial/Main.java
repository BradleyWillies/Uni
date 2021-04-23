package uk.ac.aston.jpc.unit6.tutorial;
import static uk.ac.aston.jpc.unit6.tutorial.SteppingRange.range;

public class Main {
	public static void main(String[] args) {
		SizedIterable<Integer> r = range(5,5,101);
		for(int x:r) {
			System.out.println(x);
		}
		System.out.println("There are " + r.size() + " numbers!");
	}
}
