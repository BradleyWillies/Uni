package uk.ac.aston.jpc.dpatterns.fmethod;

import java.io.IOException;

public class FMethodMain {

	public static void main(String[] args) {
		AbstractCommandReader cmdReader;
		if (args.length > 0 && args[0].trim().toLowerCase().equals("dryrun")) {
			cmdReader = new DryRunCommandReader();
		} else {
			cmdReader = new ExecutingCommandReader();
		}

		try {
			cmdReader.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
