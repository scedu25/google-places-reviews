package GooglePlacesAPI.Google;

import com.google.maps.GeoApiContext;
import com.google.maps.NearbySearchRequest;
import com.google.maps.NearbySearchRequest.Response;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;

public class PlacesAPI {

	public static void main(String[] args) {
		GeoApiContext.Builder builder = new GeoApiContext.Builder();
		builder.apiKey("AIzaSyAgRB5JK4mOOUv_QtAkwRzj8ml5LvClxUI");
		GeoApiContext context = builder.build();
		builder.queryRateLimit(10);
		LatLng chicago = new LatLng(41.85, -87.65);
		NearbySearchRequest query = PlacesApi.nearbySearchQuery(context, chicago)
											.type(PlaceType.RESTAURANT, PlaceType.LODGING);
		NearbySearchRequest.Response response = new Response();
	}
}
