module jpd.lab2 {
	// We need these two modules from JavaFX
	requires javafx.controls;
	requires transitive javafx.graphics;

	// We need AWT
	requires java.desktop;

	// We have to export our own package so JavaFX can access it
	exports uk.ac.aston.jpd.lab3.acint;
}