package testApache;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.BreakIterator;
import java.util.ArrayList;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;


/**
 * Class to practice using the wordnet api
 * 
 * @author dustin
 * 
 */
public class TestWordnet {

	// to-do this should be replaced by wordnet's representation for each of
	// these
	enum WORDTYPE {
		NOUN, VERB, ADJECTIVE, ADVERB, ARTICLE
	};

	public static void main(String[] args) {
		String exampleSentence = "Selling amazing tickets for a cool concert tonight.";

		//MaxentTagger tagger = new MaxentTagger("taggers/english-bidirectional-distsim.tagger");
		MaxentTagger tagger = new MaxentTagger("taggers/english-left3words-distsim.tagger");
		
		String tagged = tagger.tagString(exampleSentence);
		
		System.out.println(tagged);
		
		System.out.println("[Starting Sentence] " + exampleSentence);
		for (String sim : similarSentences(exampleSentence)) {
			System.out.println("[New Sentence]     " + sim);
		}

		System.out.println("---EnD oF PrOgRaM---");
	}

	/**
	 * Given a sentence, this class will return similar sentences, where each
	 * sentence is the same except 1 adjective will be replaced with a synonym
	 * from wordnet.
	 * 
	 * @param sentence
	 * @return new sentences
	 */
	private static ArrayList<String> similarSentences(String sentence) {
		ArrayList<String> similarSentences = new ArrayList<String>();

		
		
		try {
			testDictionary("doesn'tnatter");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// tokenize string into an array of words
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<WORDTYPE> wordsTypes = new ArrayList<WORDTYPE>();
		BreakIterator boundary = BreakIterator.getWordInstance();
		boundary.setText(sentence);
		String word = "";
		int wordStartIndex = 0;
		while (boundary.next() != BreakIterator.DONE) {
			word = sentence.substring(wordStartIndex, boundary.current());
			if (!word.trim().isEmpty()) { // ignore any whitespace between words
				words.add(word);
				// look up the word in wordnet
				
			}
			wordStartIndex = boundary.current();
		}

		return similarSentences;

	}

	private static WORDTYPE testDictionary(String originalWord) throws IOException {
		// construct the URL to the Wordnet dictionary directory
		String wnhome = System.getenv("WNHOME");
		String path = wnhome + File.separator + "dict";
		System.out.println("Trying path: "+path);
		URL url = new URL("file", null, path);
		// construct the dictionary object and open it
		IDictionary dict = new Dictionary(url);
		dict.open();

		// look up first sense of the word "dog"
		//IIndexWord idxWord = dict.getIndexWord("dog", POS.NOUN);
		IIndexWord idxWord = dict.getIndexWord("dog", POS.NOUN);
		
		IWordID wordID = idxWord.getWordIDs().get(0);
		IWord word = dict.getWord(wordID);
		System.out.println("Id = " + wordID);
		System.out.println("Lemma = " + word.getLemma());
		System.out.println("Gloss = " + word.getSynset().getGloss());

		return WORDTYPE.ADJECTIVE;
	}

}
