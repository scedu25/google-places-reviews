package GooglePlacesAPI.Google;

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
 *
 */
public class App 
{
	public static List<Place> hotels;
	public static List<Place> restaurants;
	
	public static void getReviews(List<Place> places, String type) {
		for (Place place : places) {
			BufferedWriter bw = null;
			FileWriter fw = null;
			//C:\\Users\\EDU\\workspace\\Google\\
			String filename = "C:\\Users\\EDU\\workspace\\Google\\" + type + "\\" + place.getName().replace('/', ' ') + ".txt";
			//String filename2 = "/home/ad.ilstu.edu/ewdu/Google/" + type + "/hotels.txt";
			String content = place.getName() + "\n";
			//String content2 = "";

        	Place detailedPlace = place.getDetails(); // sends a GET request for more details

        	/*
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
            for (Review review : detailedPlace.getReviews()) {
                //System.out.println("Author: " + review.getAuthor());
                //System.out.println("Rating: " + review.getRating());
                //System.out.println("Time: " + review.getTime());
                //System.out.println("Text: " + review.getText() + "\n");
            	//String author = review.getAuthor();
            	int rating = review.getRating();
            	//long time = review.getTime();
            	String text = review.getText();
    			//content += ("Author: " + author + "\n");
                content += ("Rating: " + rating + "\n");
                //content += ("Time: " + time + "\n");
                content += ("Text: " + text + "\n");
                //content2 += ("Rating: " + rating + "\n" + "Text: " + text + "\n");
            }
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
			/**try {
				fw = new FileWriter(filename2, true);
				bw = new BufferedWriter(fw);
				bw.write(content2);
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
			}*/
		}
	}
	
	public static void getNames(List<Place> places, String type) {
		String content = "";
		for (Place place : places) {
			BufferedWriter bw = null;
			FileWriter fw = null;
			String filename = "C:\\Users\\EDU\\workspace\\Google\\" + type + ".txt";
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
	
    public static void main( String[] args )
    {
    	//Param.name("pagetoken")
    	//Default Chicago location: (41.85, -87.65)
    	Random rand = new Random();
    	double lat = rand.nextDouble()* 10 + 41.35;
    	double lng = rand.nextDouble() * 10 - 88.15;
    	GooglePlaces client = new GooglePlaces("AIzaSyAgRB5JK4mOOUv_QtAkwRzj8ml5LvClxUI");
    	hotels = client.getNearbyPlaces(lat, lng, 50000, 60, 
    					TypeParam.name("types").value(Types.TYPE_LODGING));
    	restaurants = client.getNearbyPlaces(lat, lng, 50000, 60, 
    					TypeParam.name("types").value(Types.TYPE_RESTAURANT));
    	/*for (Place place : hotels) {
	    	Place detailedPlace = place.getDetails(); // sends a GET request for more details
	
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
	        for (Review review : detailedPlace.getReviews()) {
	            System.out.println("Author: " + review.getAuthor());
	            System.out.println("Rating: " + review.getRating());
	            System.out.println("Time: " + review.getTime());
	            System.out.println("Text: " + review.getText() + "\n");
	        }
    	}*/
        getReviews(hotels, "hotels");
        getReviews(restaurants, "restaurants");
    	//getNames(hotels, "hotels3");
    	//getNames(restaurants, "restaurants3");
    }
    
}
