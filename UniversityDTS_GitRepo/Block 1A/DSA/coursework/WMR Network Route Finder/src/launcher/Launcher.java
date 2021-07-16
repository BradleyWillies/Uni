package launcher;


import controllers.WMRController;
import wmr.TUI;

public class Launcher {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		TUI trainTUI = new TUI(new WMRController());
	}

}
