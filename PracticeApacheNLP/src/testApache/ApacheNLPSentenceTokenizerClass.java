package testApache;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;


/**
 * 
 * This class is a simple sentence tokenizer. You can pass in the location 
 * of the tokenizer bin (of Apache NLP), that will help parse the text.
 * @author Sriram
 *
 */
public class ApacheNLPSentenceTokenizerClass {
	/**
	 * @ params N/A
	 */
	private InputStream sentenceInputStream;
	private SentenceModel theSentenceModel;
	private SentenceDetectorME sentenceDetector;
	
	private String stringLocationOfTokenizer;
	
	
	/**
	 * @param inputLocationOfSentenceTok
	 */
	public ApacheNLPSentenceTokenizerClass(String inputLocationOfSentenceTok)
	{
		stringLocationOfTokenizer =  inputLocationOfSentenceTok;
		
		try{
			sentenceInputStream = 
					new FileInputStream(stringLocationOfTokenizer); //"C:\\PROFESSIONAL\\Gaudium Labs\\NLPJarFiles\\ApacheNLP\\apache-opennlp-1.5.3\\bin\\en-sent.bin");
			theSentenceModel = new SentenceModel(sentenceInputStream);			
		}//end try
		catch (Exception e){
			System.out.println(e);
		}//end catch
		finally{
			if(sentenceInputStream != null){
				try{
					sentenceInputStream.close();
				}
				catch(IOException e){
					this.quickPrint(e);
				}
			}//end if(sentenceInputStream != null){			
		}//end finally
		sentenceDetector = new SentenceDetectorME(theSentenceModel);
	}//end CONSTRUCTOR SentenceTokenizerClass()
	
	/**===========================================================================
	 * @param paraString
	 * @return String[] containing the sentences that were tokenized
	 */
	public String[] sentenceTokenizeFromPara(String paraString){
		String foundSentences[] = sentenceDetector.sentDetect(paraString);
		return foundSentences;		
	}//end function sentenceTokenizeFromPara
	
	/**===========================================================================
	 * @ params Object someObj = the object that will be printed.
	 */
	public void quickPrint(Object someObj){
		System.out.println(someObj);
	}//end function quickPrint
}
