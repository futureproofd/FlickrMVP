package to.marcus.FlickrMVP.data.event;

import android.util.Log;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import to.marcus.FlickrMVP.model.PhotosResponse;
import to.marcus.FlickrMVP.network.ApiService;

/**
 * Created by marcus on 15/04/15
 * request handler for all API calls. Currently using GetRecent and Search
 */

public class ApiRequestHandler {
    private final Bus bus;
    private final ApiService apiService;
    private static final String TAG = ApiRequestHandler.class.getSimpleName();

    //injections 'injected' further via constructor
    public ApiRequestHandler(Bus bus, ApiService apiService) {
        this.bus = bus;
        this.apiService = apiService;
    }

    @Subscribe
    public void onImagesRequested(final ImagesRequestedEvent event) {
        apiService.getRecent("flickr.photos.getRecent",
                "4b37ee018d17b1aa7cd793ede2ea7ee7",
                "url_s",
                "json",
                "1",
                new Callback<PhotosResponse>() {
                    @Override
                    public void success(PhotosResponse photosResponse, Response response) {
                        bus.post(new ImagesReceivedEvent(photosResponse.getPhotosObject().getPhotos()));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, " " + error.getUrl());
                        Log.e(TAG, " " + error.getMessage());
                        bus.post(new ImagesFailedEvent("failed :( "));
                    }
                });
    }

    @Subscribe
    public void onSearchRequested(final SearchRequestedEvent event) {
        //this shoud take a string as the result( from the search )
        final String searchTerm = event.getResult();
        apiService.getSearch("flickr.photos.search",
                "4b37ee018d17b1aa7cd793ede2ea7ee7",
                "url_s",
                searchTerm,
                "json",
                "1",
                new Callback<PhotosResponse>() {
                    @Override
                    public void success(PhotosResponse photosResponse, Response response) {
                        bus.post(new SearchReceivedEvent(photosResponse.getPhotosObject().getPhotos()));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, " " + error.getUrl());
                        Log.e(TAG, " " + error.getMessage());
                        bus.post(new ImagesFailedEvent("failed :( "));
                    }
                });
    }
}