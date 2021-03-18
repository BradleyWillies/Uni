package uk.ac.aston.jpc.lab1.life;

import uk.ac.aston.jpc.lab1.life.sim.World;

public class Launcher {

	public static void main(String[] args) throws InterruptedException {
		World w = random();
		int counter = 1;
		
		while(true) {
			System.out.println("--- " + counter);
			System.out.println(w.toString());
			w = w.nextGeneration();
			counter++;
			
			Thread.sleep(500);
		}
	}
	
	public static World stillLife() {
		return new World(
				"    ",
				" xx ",
				" xx ",
				"    "
				);
	}

	public static World oscillator() {
		return new World(
				"     ",
				"     ",
				" xxx ",
				"     ",
				"     "
				);
	}

	public static World spaceship() {
		return new World(
				"       ",
				"  x    ",
				"   x   ",
				" xxx   ",
				"       ",
				"       ",
				"       ",
				"       "
				);
	}

	public static World rPentomino() {
		return new World(
				"           ",
				"           ",
				"     xx    ",
				"    xx     ",
				"     x     ",
				"           ",
				"           ",
				"           "
				);
	}
	
	public static World random() {
		return new World(
				"       x   ",
				"  x        ",
				"     xx    ",
				"   xxx    x",
				"     x     ",
				"           ",
				" x    xx   ",
				"       x   "
				);
	}

}
