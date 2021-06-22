package uk.ac.aston.jpc.dpatterns.fmethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class AbstractCommandReader {

	// 1. TODO - abstract method for creating the command

	public void run() throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"))) {
			System.out.println("Type one of 'left', 'right', 'up', 'down' or 'exit'.");

			System.out.print("> ");
			String line;
			while ((line = br.readLine()) != null) {
				line = line.trim().toLowerCase();
				
				// 2. TODO - create and run the command based on the line

				System.out.print("> ");
			}
		}
	}

}
