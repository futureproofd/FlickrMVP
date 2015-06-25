package to.marcus.FlickrMVP.network;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import to.marcus.FlickrMVP.model.PhotosResponse;

/**
 * Created by marcus on 27/04/15.
 */

public interface ApiService {

    String method = "flickr.photos.getRecent";
    static final String API_KEY = "4b37ee018d17b1aa7cd793ede2ea7ee7";
    static final String EXTRA_SMALL_URL = "url_s";

    @GET("/rest/")
    void getRecent(@Query("method") String method,
                   @Query("api_key") String API_KEY,
                   @Query("extras") String EXTRA_SMALL_URL,
                   @Query("format") String format,
                   @Query("nojsoncallback") String set,
                   Callback<PhotosResponse> callback
    );
}
