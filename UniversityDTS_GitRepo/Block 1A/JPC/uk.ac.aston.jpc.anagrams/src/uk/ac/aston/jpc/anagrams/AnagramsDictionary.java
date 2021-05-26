package uk.ac.aston.jpc.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Dictionary of words for the Anagram game.
 */
public class AnagramsDictionary {
	
	private List<String> wordList = new ArrayList<String>();
	private Map<String, List<String>> lettersToWord = new HashMap<String, List<String>>();
	private Set<String> wordSet = new HashSet<>();
	private static final int MIN_NUM_ANAGRAMS = 5;
	private Random rnd = new Random();
	private Map<Integer, List<String>> sizeToWords = new HashMap<Integer, List<String>>();

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
				// populate lettersToWord
				String sortedWord = new String(sortLetters(word));
				if(lettersToWord.containsKey(sortedWord)) {
					lettersToWord.get(sortedWord).add(word);
				}
				else {
					List<String> newList = new ArrayList<>(Arrays.asList(word));
					lettersToWord.put(sortedWord, newList);
				}
				// populate wordSet
				wordSet.add(word);
				// populate sizeToWords
				if(sizeToWords.containsKey(word.length())) {
					sizeToWords.get(word.length()).add(word);
				}
				else {
					List<String> newNewList = new ArrayList<>(Arrays.asList(word));
					sizeToWords.put(word.length(), newNewList);
				}
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
		int start = rnd.nextInt(wordList.size());
		int i = start;
		do {
			String word = wordList.get(i);
			List<String> anagrams = getAnagramsWithOneMoreLetter(word);
			if(anagrams.size() >= MIN_NUM_ANAGRAMS) {
				return word;
			}
			i++;
			if (i > wordList.size()) {
				i = 0;
			}
		} while (i != start);
		return "";
	}

	/**
	 * Returns a new list with all the known anagrams of a certain word.
	 */
	public List<String> getAnagrams(String word) {
		List<String> candidates = new ArrayList<>();
//		for(String possibleWord : wordList) {
//			if(Arrays.equals(sortLetters(possibleWord), sortLetters(word))) {
//				candidates.add(possibleWord);
//			}
//		}
		String sortedWord = new String(sortLetters(word));
		if(lettersToWord.containsKey(sortedWord)) {
			candidates = lettersToWord.get(sortedWord);
		}
		return candidates;
	}

	/**
	 * Returns a new list with all the words that are anagrams of a certain word
	 * after adding a letter.
	 */
	public List<String> getAnagramsWithOneMoreLetter(String string) {
		List<String> candidates = new ArrayList<>();
		for(char c = 'a' ; c <= 'z' ; c = (char) (c + 1)) {
			candidates.addAll(getAnagrams(string + c));
		}
		return candidates;
	}

	/**
	 * Is this a valid word, which is not a substring of the base word?
	 */
	public boolean isGoodWord(String word, String base) {
		if(wordSet.contains(word) && !word.contains(base)) {
			return true;
		}
		return false;
	}

}
