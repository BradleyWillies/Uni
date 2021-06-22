module uk.ac.aston.jpc.dpatterns {
	requires transitive java.desktop;

	requires javafx.controls;
	requires transitive javafx.graphics;

	exports uk.ac.aston.jpc.dpatterns.afactory;
	exports uk.ac.aston.jpc.dpatterns.afactory.javafx;
	exports uk.ac.aston.jpc.dpatterns.afactory.swing;
	exports uk.ac.aston.jpc.dpatterns.fmethod;
	exports uk.ac.aston.jpc.dpatterns.singleton;
}