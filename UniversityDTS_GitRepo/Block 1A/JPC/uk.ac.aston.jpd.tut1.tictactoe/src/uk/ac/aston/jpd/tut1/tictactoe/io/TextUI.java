package uk.ac.aston.jpd.tut1.tictactoe.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Provides commands for implementing a basic text UI.
 * <p>
 * It can read text input from the user in a certain character set through an
 * {@link InputStream} (typically {@code System.in}). It can also write text to
 * an {@link OutputStream} (typically {@code System.out}).
 * <p>
 * This class is intended to abstract away details about I/O until we get to the
 * relevant lectures at the end of the module.
 *
 * @author Antonio García-Domínguez
 */
public class TextUI {

	private final BufferedReader reader;
	private final PrintWriter outputWriter;
	private final PrintWriter errorWriter;

	/**
	 * Convenience constructor which uses the standard input, output, and error
	 * streams, and the UTF-8 encoding.
	 */
	public TextUI() {
		this(System.in, System.out, System.err, Charset.forName("UTF-8"));
	}

	/**
	 * Creates an instance that reads from {@code in}, prints regular messages on
	 * {@code out}, and prints error messages on {@code err}. Text is turned into
	 * bytes following the character set {@code charset}.
	 *
	 * @param in      Input stream to read user input from.
	 * @param out     Output stream where regular messages and prompts should be
	 *                printed.
	 * @param err     Output stream where error messages should be printed.
	 * @param charset Character set that should be used to convert characters into
	 *                bytes.
	 */
	public TextUI(InputStream in, OutputStream out, OutputStream err, Charset charset) {
		this.reader = new BufferedReader(new InputStreamReader(in, charset));
		this.outputWriter = new PrintWriter(new OutputStreamWriter(out, charset), true);
		this.errorWriter = new PrintWriter(new OutputStreamWriter(err, charset), true);
	}

	/**
	 * Reads a line from the input stream, and returns it.
	 *
	 * @return A string with all characters up to the next line separator
	 *         ({@see System#lineSeparator()}), or {@code null} if reached the end
	 *         of the input or there was an error.
	 */
	public String readLine() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Writes a message to the output stream, adding a line terminator at the end.
	 * 
	 * @param line Message to be printed.
	 */
	public void writeMessage(String line) {
		outputWriter.println(line);
	}

	/**
	 * Writes a message to the output stream, adding a colon and a space at the end.
	 * 
	 * @param line Message to be printed.
	 */
	public void prompt(String line) {
		outputWriter.print(line + ": ");
		outputWriter.flush();
	}

	/**
	 * Writes a message to the error stream, adding a line terminator at the end.
	 * 
	 * @param line Message to be printed.
	 */
	public void writeError(String line) {
		errorWriter.println(line);
	}

	/**
	 * Reads a line from the input stream, and splits it into {@code expected}
	 * integers by whitespace.
	 *
	 * @param expected Expected number of integers in the line.
	 * @return Array of integers with the numbers read from the line, or
	 *         {@code null} if the end of the input was reached. Missing numbers are
	 *         replaced with zeros, and additional numbers are ignored.
	 */
	public int[] readNumbers(int expected) {
		// allocate array
		int[] numbers = new int[expected];
		// read a line
		String line = readLine();
		if (line == null) { return null; }
		// create a Scanner on the line
		Scanner scanner = new Scanner(line);
		// initialize array with hasNextInt() / nextInt()
		int index = 0;
		while (scanner.hasNextInt()) {
			numbers[index] = scanner.nextInt();
			index++;
		}
		// return array
		scanner.close();
		return numbers;
	}
}
