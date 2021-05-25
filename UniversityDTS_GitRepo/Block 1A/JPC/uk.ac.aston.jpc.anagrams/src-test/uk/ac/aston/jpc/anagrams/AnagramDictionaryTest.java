package uk.ac.aston.jpc.anagrams;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aston.jpc.anagrams.AnagramsController;
import uk.ac.aston.jpc.anagrams.AnagramsDictionary;

public class AnagramDictionaryTest {

	private AnagramsDictionary dictionary;

	@Before
	public void setUp() throws IOException {
		dictionary = new AnagramsDictionary(
			getClass().getResourceAsStream(AnagramsController.WORDS_LIST));
	}

	@Test
	public void postIsAnagramOfStop() {
		assertTrue(dictionary.getAnagrams("stop").contains("post"));
	}

	@Test
	public void opstIsNotAnagramOfStop() {
		assertFalse(dictionary.getAnagrams("stop").contains("opst"));
	}

	@Test
	public void isGoodWordWorks() {
		final String base = "post";

		assertTrue(dictionary.isGoodWord("nonstop", base));
		assertFalse(dictionary.isGoodWord("poster", base));
		assertFalse(dictionary.isGoodWord("lamp post", base));
		assertTrue(dictionary.isGoodWord("spots", base));
		assertFalse(dictionary.isGoodWord("apostrophe", base));
	}

	@Test
	public void oneMoreLetterAnagrams() {
		assertTrue(dictionary.getAnagramsWithOneMoreLetter("post").contains("spots"));
	}
}
