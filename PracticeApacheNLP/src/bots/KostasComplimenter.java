package bots;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.PostUpdate;
import facebook4j.ResponseList;
import facebook4j.User;
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
		//Facebook facebook = new FacebookFactory().getInstance();
		// Use default values for oauth app id.
		// facebook.setOAuthAppId("", "");// SUPER IMPORTANT
		// ?? ONLY DONE ONCE ?? WHAT IS IT ASSOCIATED TO ? MY LOGIN?

		// Get an access token from:
		// https://developers.facebook.com/tools/explorer
		// Copy and paste it below.

		try {

			String accessTokenString = "CAALiZAjQcQ18BABGvXOAWHZAVPYXbIRjB0WYJkQpZCQnnPt4evu8ZABLTVNwEvzzxIxFxoP4ZCUN9yuDwVZCeExJbEgsc2MYRSWZB3GfnLnWS78WYNZBysyOcY4din1sZAFCuPYEvk20MgVFtfW5fXfaRkhYAvooUMOfpdkel1XCZCziRIxZCUaohfEr0C5MdxEzo5jht9UaE40DCfXwBPqfWg3";
			AccessToken at = new AccessToken(accessTokenString);
		
			facebook.setOAuthAccessToken(at);
			ResponseList<User> results = facebook.searchUsers("kostas");
			
			for (User kostas : results) {
				System.out.println("Found a user named "+kostas.getFirstName()+ " "+kostas.getLastName());
				for (Post p : facebook.getFeed(kostas.getId())) {
					System.out.println("New Post: "+p.getMessage());
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
