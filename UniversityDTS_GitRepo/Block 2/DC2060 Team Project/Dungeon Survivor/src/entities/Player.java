package entities;

public class Player {
	
	int health = 100;
	int attackDamage = 50;
	int numHealthPotions = 3;
	int healthPotionHealAmount = 30;
	int healthPotionDropChance = 50;	// percentage
	
	public int getHealth() {
		return health;
	}

	public int getAttackDamage() {
		return 50;
	}

	public void takeDamage(int damageTaken) {
		health -= damageTaken;
	}

	public int getNumHealthPotions() {
		return numHealthPotions;
	}

	public void heal() {
		health += healthPotionHealAmount;
		if(health > 100) {
			health = 100;
		}
		numHealthPotions -= 1;
	}

	public int getHealthPotionHealAmount() {
		return healthPotionHealAmount;
	}

	public int getHealthPotionDropChance() {
		return healthPotionDropChance;
	}

	public void findHealthPotion() {
		numHealthPotions++;
	}
	
}
