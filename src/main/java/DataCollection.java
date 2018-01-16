import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;
import se.walkercrou.places.Review;
import se.walkercrou.places.TypeParam;
import se.walkercrou.places.Types;

/**
 * This class retrieves hotel and restaurant reviews from Google Places API, getting a maximum of five reviews 
 * per hotel/restaurant. A text file is created for each place with rating and text of review written to the file.
 */
public class DataCollection 
{
	public static List<Place> hotels;
	public static List<Place> restaurants;
	
	/**
	 * This function retrieves reviews from Place objects in places List using the Place's getDetails() method.
	 * The getDetails() method returns a Place with more details, including reviews and author, time posted, 
	 * rating (1-5 scale) assigned by reviewer. 
	 * @param places
	 * @param type
	 */
	public static void getReviews(List<Place> places, String type) {
		for (Place place : places) {
			BufferedWriter bw = null;
			FileWriter fw = null;
			String filename = type + "\\" + place.getName().replace('/', ' ') + ".txt";
			String content = place.getName() + "\n";

        	Place detailedPlace = place.getDetails(); // sends a GET request for more details

        	/* The following code, when uncommented, will print several Place details to console before writing
        	 * review details to file.
            System.out.println("\n\nID: " + detailedPlace.getPlaceId());
            System.out.println("Name: " + detailedPlace.getName());
            System.out.println("Location: " + detailedPlace.getLatitude() + ", " + detailedPlace.getLongitude());
            System.out.println("Phone: " + detailedPlace.getPhoneNumber());
            System.out.println("International Phone: " + detailedPlace.getInternationalPhoneNumber());
            System.out.println("Website: " + detailedPlace.getWebsite());
            System.out.println("Always Opened: " + detailedPlace.isAlwaysOpened());
            System.out.println("Status: " + detailedPlace.getStatus());
            System.out.println("Google Place URL: " + detailedPlace.getGoogleUrl());
            System.out.println("Price: " + detailedPlace.getPrice());
            System.out.println("Address: " + detailedPlace.getAddress());
            System.out.println("Vicinity: " + detailedPlace.getVicinity());
            System.out.println("Reviews: " + detailedPlace.getReviews().size() + "\n");
            */
        	
        	/*
        	 * This for loop retrieves each review from the 5 maximum reviews obtainable from the current Place.
        	 * It will add the following to content string.
        	 * Rating: *rating*
        	 * Text: *text*
        	 */
            for (Review review : detailedPlace.getReviews()) {
            	int rating = review.getRating();
            	String text = review.getText();
                content += ("Rating: " + rating + "\n");
                content += ("Text: " + text + "\n");
            }
            /*
             * This try-catch block will write the content string to a new file named after the name of the Place.
             */
			try {
				fw = new FileWriter(filename);
				bw = new BufferedWriter(fw);
				bw.write(content);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try {
					bw.close();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * This function is used to append to a text file listing the names of all places for which data was collected.
	 * This text file is used for future Named Entity Recognition (NER) purposes, which would ideally allow the 
	 * program to recognize these entities as either hotels or restaurants.
	 * @param places
	 * @param type
	 */
	public static void getNames(List<Place> places, String type) {
		String content = "";
		for (Place place : places) {
			BufferedWriter bw = null;
			FileWriter fw = null;
			String filename = type + ".txt";
			content += place.getName() + "\n";
			try {
				fw = new FileWriter(filename);
				bw = new BufferedWriter(fw);
				bw.write(content);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try {
					bw.close();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * New GooglePlaces client is established using Google Places API key. Latitude and longitude of search 
	 * location is provided, and search radius is set at 50,000 meters (50 km).
	 * Maximum of 60 results are returned per function call. Current latitude and longitude is approximately set
	 * at San Francisco. Types are specified as TYPE_LODGING (hotels) and TYPE_RESTAURANT (restaurant).
	 */
    public static void main( String[] args )
    {
    	//Default Chicago location: (41.85, -87.65)
    	Random rand = new Random();
    	double lat = rand.nextDouble()* 1.5 + 37.36;
    	double lng = rand.nextDouble() * 1.5 - 122.15;
    	GooglePlaces client = new GooglePlaces("AIzaSyAgRB5JK4mOOUv_QtAkwRzj8ml5LvClxUI");
    	hotels = client.getNearbyPlaces(lat, lng, 50000, 60, 
    					TypeParam.name("types").value(Types.TYPE_LODGING));
    	restaurants = client.getNearbyPlaces(lat, lng, 50000, 60, 
    					TypeParam.name("types").value(Types.TYPE_RESTAURANT));
        getReviews(hotels, "hotels");
        getReviews(restaurants, "restaurants");
    	//getNames(hotels, "hotels3");
    	//getNames(restaurants, "restaurants3");
    }
    
}
