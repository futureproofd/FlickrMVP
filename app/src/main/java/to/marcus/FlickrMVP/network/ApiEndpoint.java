package to.marcus.FlickrMVP.network;

import retrofit.Endpoint;

/**
 * Created by marcus on 4/6/2015.
 * Flickr API
 */

public class ApiEndpoint implements Endpoint {
    private static final String ENDPOINT = "https://api.flickr.com/services";

    @Override
    public String getUrl() {
        return ENDPOINT;
    }

    @Override
    public String getName(){
        return "Flickr Endpoint";
    }
}
