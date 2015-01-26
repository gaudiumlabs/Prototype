import facebook4j.*;
import facebook4j.auth.AccessToken;
public class main {

	public static void main(String[] args) {
		Facebook facebook = new FacebookFactory().getInstance();
	    // Get an access token from: 
	    // https://developers.facebook.com/tools/explorer
	    // Copy and paste it below.
	    String accessTokenString = "CAACEdEose0cBALzlZCRhKrrn2Fh3asYObX4xDLn2IXZCUsxRc8ggsIOKOpaEmw1WTTIUnUQMFEVm1bFdS5rjFpz3ochoM1PIrtpWtTcP6MmHG3mq6P43EtPz4OpZBoUiXTDifA7TuY5RSy8ZB1DZAcYuygN2PmkDTj3J7nsTHIB8TK7aB9YsGeXvnyO103dNJJMMfhMNWqeK7tkxNRCfncMtWf4VlnZBsZD";
	    AccessToken at = new AccessToken(accessTokenString);
	    // Set access token.
	    facebook.setOAuthAccessToken(at);

		try {
		// Search by name
		//ResponseList<Place> results = facebook.searchPlaces("coffee");

		// You can narrow your search to a specific location and distance
		GeoLocation center = new GeoLocation(40.511080, -75.392639);
		int distance = 10000;
		ResponseList<Place> results = facebook.searchPlaces("coffee", center, distance);
		
		
		for(Place place : results){
			System.out.println();
			System.out.println();
			System.out.println(place);
			System.out.println();
			System.out.println();
			
			ResponseList<Post> feed = facebook.getFeed(place.getId());
			for(Post post : feed){
				System.out.println(post);
			}
		}
		
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
