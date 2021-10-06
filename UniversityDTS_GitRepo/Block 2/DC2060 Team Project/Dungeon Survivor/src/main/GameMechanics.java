package main;

import java.util.Random;

import entities.Enemy;
import entities.Player;
import gui.GUI;

public class GameMechanics {
	
		boolean running = true;
		int enemiesDefeatedCounter = 0;
		GUI gui;
		Enemy enemy;
		int buttonPress;
		Random random = new Random();
		Player player = new Player();
		
		public GameMechanics(GUI gui2) {
			gui = gui2;
		}
		
		public void startNewRoom() {
			gui.appendText("---------------------------------------");
			
			// Spawn enemy
			Enemy newEnemy = new Enemy();
			enemy = newEnemy;
			newEnemy.spawn(gui);
			battle();
		}
		
		public void battle() {
			while(running) {
				// Main game loop
				while(enemy.getHealth() > 0){
					mainMenu();
					if(buttonPress == 1) {
						attack();
						if(player.getHealth() < 1) {
							die();
							running = false;
							return;
						}
					}
					else if(buttonPress == 2) {
						drinkPotion();
					}
					else if(buttonPress == 3) {
						run();
						startNewRoom();
						return;
					}
				}
				enemyDefeated();			
			}
		}
		
		public void mainMenu() {
			gui.appendText("\tYour HP: " + player.getHealth());
			gui.appendText("\t" + enemy.getName() + "'s HP: " + enemy.getHealth());
			gui.appendText("\n\tWhat would you like to do?");
			gui.appendText("\t1. Attack");
			gui.appendText("\t2. Drink health potion");
			gui.appendText("\t3. Run!\n");
			buttonPress = gui.getButtonPress();
		}
		
		public void attack() {
			int damageDealt = random.nextInt(player.getAttackDamage());
			int damageTaken = random.nextInt(enemy.getAttackDamage());
			enemy.takeDamage(damageDealt);
			player.takeDamage(damageTaken);
			// if the enemy is defeated, iterate the counter
			if(enemy.getHealth() < 1) {
				enemiesDefeatedCounter++;
			}
			gui.appendText("\t> You strike the " + enemy.getName() + " for " + damageDealt + " damage.");
			gui.appendText("\t> You receive " + damageTaken + " in retaliation!\n");
		}
		
		public void die() {
			gui.appendText("\nYou have taken too much damage, you are too weak to go on!");
			gui.appendText("You limp out of the dungeon, weak from battle.");
			gui.appendText("You defeated " + enemiesDefeatedCounter + (enemiesDefeatedCounter == 1 ? " enemy!" :  " enemies!\n"));
			endGame();
		}
		
		public void drinkPotion() {
			if(player.getNumHealthPotions() > 0) {
				player.heal();
				gui.appendText("\t> You drink a health potion, healing yourself for " + player.getHealthPotionHealAmount() + "HP."
									+ "\n\t> You now have " + player.getHealth() + "HP."
									+ "\n\t> You have " + player.getNumHealthPotions() + " health " + (player.getNumHealthPotions() == 1 ? "potion" : "potions") + " left.\n");
			}
			else {
				gui.appendText("\t> You have no health potions left!\n");
			}
		}
		
		public void run() {
			gui.appendText("\tYou run away from the " + enemy.getName() + "!");
			if(random.nextInt(10) < 5) {
				gui.appendText("\tOUCH! While sheepishly running from your foe you stub your toe!\n\t-5HP");
				player.takeDamage(5);
			}
		}
		
		public void enemyDefeated() {
			gui.appendText("---------------------------------------");
			gui.appendText(" # " + enemy.getName() + " was defeated! #");
			gui.appendText(" # You have " + player.getHealth() + " HP left. #");
			if(random.nextInt(100) < player.getHealthPotionDropChance()) {
				player.findHealthPotion();
				gui.appendText(" # The " + enemy.getName() + " dropped a health potion! #");
				gui.appendText(" # You now have " + player.getNumHealthPotions() + " health " + (player.getNumHealthPotions() == 1 ? "potion" : "potions") + ". #");
			}
			gui.appendText("---------------------------------------");
			gui.appendText("What would you like to do now?");
			gui.appendText("1. Continue fighting");
			gui.appendText("2. Exit dungeon\n");		
			
			while(!(buttonPress == 4)) {
				buttonPress = gui.getButtonPress();
				if(buttonPress == 1) {
					gui.appendText("---------------------------------------");
					gui.appendText("You continue on your adventure!");
					startNewRoom();
					buttonPress = 4;
				}
				else if(buttonPress == 2) {
					gui.appendText("You exit the dungeon after successfully defeating " + enemiesDefeatedCounter + (enemiesDefeatedCounter == 1 ? " enemy!\n" :  " enemies!\n"));
					endGame();
					buttonPress = 4;
					running = false;
				}
				else if(buttonPress == 3) {
					gui.appendText("Choose 1 or 2.\n");
				}
			}
		}
		
		public void endGame() {
			gui.appendText("#######################");
			gui.appendText("# THANKS FOR PLAYING! #");
			gui.appendText("#######################");
		}
}
