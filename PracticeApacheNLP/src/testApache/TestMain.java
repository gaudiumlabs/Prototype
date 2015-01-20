/**
 * 
 */
package testApache;



/**
 * @author Sriram
 *
 */
public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
						
		SentenceTokenizerClass sentenceTokenizer = new SentenceTokenizerClass();
		String resultArray[] = sentenceTokenizer.sentenceTokenizeFromPara("Sentence one. Sentence two");
		System.out.println(resultArray[1]);

	}//end main function
		
}// End class TestMain


