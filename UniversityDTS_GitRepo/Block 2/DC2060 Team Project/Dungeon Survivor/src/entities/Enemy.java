package entities;

import java.util.Random;

import gui.GUI;

public class Enemy {

	String[] type = { "Skeleton", "Zombie", "Warrior", "Assassin" };
	int maxHealth = 76;	// The random.nextInt method includes 0 to 75
	int attackDamage = 35;
	int health;
	String name;
	Random randomiser = new Random();
	
	public void spawn(GUI gui) {
		health = randomiser.nextInt(maxHealth);
		while(health == 0) {
			health = randomiser.nextInt(maxHealth);
		}
		name = type[randomiser.nextInt(type.length)];
		String aOrAn = ((name.charAt(0) == 'A' || name.charAt(0) == 'E' || name.charAt(0) == 'I' || name.charAt(0) == 'O' || name.charAt(0) == 'U') ? "An" : "A");
		gui.appendText("\t# " + aOrAn + " " + name + " has appeared! #\n");
	}
	
	public int getHealth() {
		return health;
	}

	public String getName() {
		return name;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void takeDamage(int damageDealt) {
		health -= damageDealt;
	}
}
