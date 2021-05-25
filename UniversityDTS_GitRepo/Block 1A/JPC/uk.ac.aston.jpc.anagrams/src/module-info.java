module jpd.lab2 {
	// We need these two modules from JavaFX
	requires javafx.controls;
	requires transitive javafx.graphics;
	requires javafx.fxml;

	// We need AWT
	requires java.desktop;

	// We need JUnit
	requires junit;

	// We have to export our own package so JavaFX can access it
	exports uk.ac.aston.jpc.anagrams;
}