/**
 * 
 */

package testApache;

import java.io.File;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.GeoLocation;
import facebook4j.Place;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;

/**
 * @author Sriram
 * 
 */
public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FacebookException {
		Facebook facebook = new FacebookFactory().getInstance();
		// Use default values for oauth app id.
		// facebook.setOAuthAppId("", "");// SUPER IMPORTANT
		// ?? ONLY DONE ONCE ?? WHAT IS IT ASSOCIATED TO ? MY LOGIN?

		// Get an access token from:
		// https://developers.facebook.com/tools/explorer
		// Copy and paste it below.

		try {

			String accessTokenString = "CAACEdEose0cBAPi0yc2jAaXbqYgm9Fq8KJOZAO96wvBEoP9OD1tjhIMhH4dGkaE0FCcG3Rm6VOznq5PXlg2LQZAauQTkpLvEE3df9egP0gvEjMKk6ZAcIy9wuKTmlNWypZA7lQZA7Aw2EFs3u4n3jgJnK34FnKwFXRXffroJEdYVn6lZAXzqR3xtUjBnvZAGdEUjoBtdzIU03rMecqGQA47";
			AccessToken at = new AccessToken(accessTokenString);
			// Set access token.

			facebook.setOAuthAccessToken(at);

			// Search by name
			// ResponseList<Place> results = facebook.searchPlaces("coffee");

			// You can narrow your search to a specific location and distance
			GeoLocation center = new GeoLocation(40.622819, -75.392085);
			int distance = 10000;
			ResponseList<Place> results = facebook.searchPlaces("yianni",
					center, distance);

			int count = 0;
			for (Place place : results) {

				appendNewLineToFile(place.getName(),
						"FacebookSearchResults.txt");
				count++;

				ResponseList<Post> feed = facebook.getFeed(place.getId());
				int postCount = 0;
				for (Post post : feed) {
					appendNewLineToFile(post.getMessage(),
							"FacebookSearchResults.txt");
					appendNewLineToFile("=========END OF POST.",
							"FacebookSearchResults.txt");
					postCount++;
					if (postCount == 20) {
						break;
					}
				}
				if (count == 5)
					break;
			}// end for loop through the places
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		appendNewLineToFile("END OF SEARCH RESULTS",
				"FacebookSearchResults.txt");

		sentenceTokenizeParagraph("FacebookSearchResults.txt",
				"SentenceTokenizedTextOfSearches.txt");

		System.out.println("\n\n EnD oF pRoGrAm \n\n");
	}// end main function

	/**
	 * Adds a new line to the text file specified
	 * 
	 * @param insertedText
	 * @param nameOfFileToWriteTo
	 * @return boolean indicating if it worked
	 */
	public static boolean appendNewLineToFile(String insertedText,
			String nameOfFileToWriteTo) {
		try {
			File fileToWriteTo = new File(nameOfFileToWriteTo);
			FileUtils.writeStringToFile(fileToWriteTo, insertedText, "UTF-8",
					true);
			FileUtils.writeStringToFile(fileToWriteTo, "\n", "UTF-8", true);

		} catch (IOException e) {
			System.out.println("Unable to access the file");
			System.out.println(e);
		} catch (Exception e) {
			System.out
					.println("NOT an IO exception, something else went wrong");
			System.out.println(e);
		}
		return true;
	}

	/**
	 * Function to return tokenize the paragraphs into discrete sentences.
	 * 
	 * @param fileNameContainingText
	 * @param destinationFileName
	 * @return boolean to indicate if the operation succeeded (true)
	 */
	private static boolean sentenceTokenizeParagraph(
			String fileNameContainingText, String destinationFileName) {
		boolean return_value = false;
		// create a sentence tokenizer class with the binary of the sentence
		// tokenization learned data binary
		ApacheNLPSentenceTokenizerClass sentenceTokener = new ApacheNLPSentenceTokenizerClass(
				"..\\NLPJarFiles\\ApacheNLP\\apache-opennlp-1.5.3\\bin\\en-sent.bin");
		try {
			File fileToReadFrom = new File(fileNameContainingText);
			String textToSentenceTokenize = FileUtils
					.readFileToString(fileToReadFrom);
			String[] resultTokenizedSentences = sentenceTokener
					.sentenceTokenizeFromPara(textToSentenceTokenize);

			for (String aSentence : resultTokenizedSentences) {
				appendNewLineToFile(aSentence, "SentenceTokenizedText.txt");
			}

			return_value = true;
		} catch (IOException e) {
			System.out.println("Unable to access the file");
			System.out.println(e);
		} catch (Exception e) {
			System.out
					.println("NOT an IO exception, something else went wrong");
			System.out.println(e);
		}

		return return_value;
	}

	

	// end function sentenceTokenizeParagraph
}// End class TestMain

