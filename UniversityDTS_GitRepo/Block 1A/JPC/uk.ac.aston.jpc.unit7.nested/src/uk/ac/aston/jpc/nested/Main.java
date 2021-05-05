package uk.ac.aston.jpc.nested;

import static uk.ac.aston.jpc.nested.v4.SteppingRange.range;

import java.util.function.Consumer;

public class Main {

	public static void main(String[] args) {
		SizedIterable<Integer> r = range(5, 5, 101);
		
//		Consumer<Integer> c = new Consumer<Integer>() {
//			@Override
//			public void accept(Integer t) {
//				System.out.println(t);
//			}
//		};
		
//		Consumer<Integer> c = t -> System.out.println(t);
		
		r.forEach(System.out::println);
		
//		for (int x : r) {
//			System.out.println(x);
//		}
		System.out.println("There are " + r.size() + " numbers.");
	}

}
