package uk.ac.aston.jpc.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Dictionary of words for the Anagram game.
 */
public class AnagramsDictionary {
	
	private List<String> wordList = new ArrayList<String>();

	/**
	 * Loads the dictionary from a source of bytes, which is exhausted and closed.
	 * 
	 * @throws IOException
	 *             An I/O error occurred while reading the source of bytes.
	 */
	public AnagramsDictionary(InputStream is) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
			String line;
			while ((line = reader.readLine()) != null) {
				// do something with the line
				String word = line.trim();
				wordList.add(word);
			}
		}
	}

	/**
	 * Creates an empty dictionary.
	 */
	public AnagramsDictionary() {
		// nothing to do!
	}
	
	/**
	 * Takes a string and outputs that string in alphabetical order
	 * 
	 * @param word
	 * @return char[] of the input string with letters in alphabetical order
	 */
	private char[] sortLetters(String inputString) {
		char[] chars = inputString.toCharArray();
		Arrays.sort(chars);
		return chars;
	}

	/**
	 * Picks a word that is fun to start from.
	 */
	public String pickGoodStarterWord() {
		return "stop";
	}

	/**
	 * Returns a new list with all the known anagrams of a certain word.
	 */
	public List<String> getAnagrams(String word) {
		List<String> candidates = new ArrayList<>();
		for(String possibleWord : wordList) {
			if(Arrays.equals(sortLetters(possibleWord), sortLetters(word))) {
				candidates.add(possibleWord);
			}
		}
		return candidates;
	}

	/**
	 * Returns a new list with all the words that are anagrams of a certain word
	 * after adding a letter.
	 */
	public List<String> getAnagramsWithOneMoreLetter(String string) {
		List<String> candidates = new ArrayList<>();
		return candidates;
	}

	/**
	 * Is this a valid word, which is not a substring of the base word?
	 */
	public boolean isGoodWord(String word, String base) {
		return true;
	}

}
