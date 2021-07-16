package launcher;


import controllers.WMRController;

public class Launcher {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		TUI trainTUI = new TUI(new WMRController());
	}

}
