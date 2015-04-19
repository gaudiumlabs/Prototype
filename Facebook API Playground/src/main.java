import facebook4j.*;
import facebook4j.auth.AccessToken;
public class main {

	public static void main(String[] args) {
		Facebook facebook = new FacebookFactory().getInstance();
	    // Get an access token from: 
	    // https://developers.facebook.com/tools/explorer
	    // Copy and paste it below.
	    String accessTokenString = "CAACEdEose0cBAHQAg3E3pP8ZBJwR1TzZC6tbVmRfGeOwyKujjfArGPvbgMMlYcV9A7eKVBzqhlZBJ19ZArw32p3SZCMIIGQZBG2OZA2s1HAkBIpUP8TiASWU2AVvODcC0lF0B3xgPLRzplEqpNZCC0putNqS2ImMZCuJKqx4ZBcu6dDIqZCabassI1TEFZB6kUbZBSn6wK82EhPG2t0qoNN9HdwHWjNGVOD9Mpv8ZD";
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
