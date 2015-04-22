package bots;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;

/**
 * This class is an exercise in using facebook's API to post to facebook. This
 * app will post compliments to Kostas for Kostas repeatedly every X time
 * interval
 * 
 * @author dustin
 * 
 */
public class KostasComplimenter {

	public static void main(String[] args) {
		Facebook facebook = new FacebookFactory().getInstance();
		
		try {

			String appTokenString = "811878542230367|r5oHJbe0lUYiSyiUNaATUNqJ9uQ";
			AccessToken at = new AccessToken(appTokenString);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
