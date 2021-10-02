package main;

import gui.GUI;

public class Launcher {

	public static void main(String[] args) {
		GUI gui = new GUI();
		GameMechanics gm = new GameMechanics(gui);
		
		gui.startGUI();
		gui.appendText("Welcome to the Dungeon!");
		gm.startNewRoom();
	}
}
