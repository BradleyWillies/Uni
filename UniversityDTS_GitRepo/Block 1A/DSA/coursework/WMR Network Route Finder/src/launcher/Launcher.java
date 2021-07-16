package launcher;

import controllers.WMRController;

/**
 * A Launcher is the entry point to the application. It has a main method which creates a TUI
 * 
 * @author Bradley Willies
 * @version 27/06/2021
 *
 */
public class Launcher {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// create the TUI and pass it a new WMRController
		TUI trainTUI = new TUI(new WMRController());
	}

}
