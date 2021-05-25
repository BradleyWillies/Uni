package uk.ac.aston.jpc.anagrams;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AnagramsController {

	/**
	 * Path from this class to the words list within the classpath.
	 */
	static final String WORDS_LIST = "words.txt";

	private class Submission {
		private final String word;
		private final boolean correct;

		public Submission(String word, boolean correct) {
			this.word = word;
			this.correct = correct;
		}

		public String getWord() {
			return word;
		}

		public boolean isCorrect() {
			return correct;
		}
	}

	private final class WordValidatingCell extends ListCell<Submission> {
		@Override
		protected void updateItem(Submission item, boolean empty) {
			super.updateItem(item, empty);

			if (empty || item == null) {
				setText(null);
				getStyleClass().remove(CORRECT_CLASS);
				getStyleClass().remove(WRONG_CLASS);
			} else if (item.isCorrect()) {
				setText(item.getWord());
				getStyleClass().add(CORRECT_CLASS);
			} else {
				setText("X " + item.getWord());
				getStyleClass().add(WRONG_CLASS);
			}
		}
	}

	private static final String CORRECT_CLASS = "correct";
	private static final String WRONG_CLASS = "wrong";

	/** Field to enter the next anagram candidate. */
	@FXML
	public TextField txtWord;

	/** List of all the correct and wrong submissions from the player. */
	@FXML
	public ListView<Submission> lstSubmissions;

	/** Property with the current word to produce anagrams from. */
	private final StringProperty currentWord = new SimpleStringProperty("");

	/** List of all the remaining known anagrams for this word. */
	private List<String> anagrams;

	private AnagramsDictionary dictionary;

	@FXML
	public void initialize() {
		lstSubmissions.setItems(FXCollections.observableArrayList());
		lstSubmissions.setCellFactory(param -> new WordValidatingCell());

		final InputStream isWords = getClass().getResourceAsStream(WORDS_LIST);
		if (isWords == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error while loading");
			alert.setContentText("Could not find the word list");
			alert.showAndWait();
			dictionary = new AnagramsDictionary();
		} else {
			try {
				dictionary = new AnagramsDictionary(isWords);
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error while loading");
				alert.setHeaderText("Failed to load word list");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
				e.printStackTrace();

				dictionary = new AnagramsDictionary();
			}
		}

		pickNextWord();
	}

	@FXML
	public void resetPressed() {
		pickNextWord();
	}

	@FXML
	public void submitPressed() {
		processWord(txtWord.getText());
	}

	private void pickNextWord() {
		currentWord.set(dictionary.pickGoodStarterWord());
		anagrams = dictionary.getAnagrams(currentWord.get());
		lstSubmissions.getItems().clear();
	}

	private void processWord(String word) {
		if (word.length() == 0) {
			return;
		}

		if (dictionary.isGoodWord(word, currentWord.get()) && anagrams.contains(word)) {
			anagrams.remove(word);
			lstSubmissions.getItems().add(new Submission(word, true));
		} else {
			lstSubmissions.getItems().add(new Submission(word, false));
		}
	}

	public StringProperty currentWordProperty() {
		return this.currentWord;
	}

	public String getCurrentWord() {
		return this.currentWordProperty().get();
	}

	public void setCurrentWord(final String currentWord) {
		this.currentWordProperty().set(currentWord);
	}

}
