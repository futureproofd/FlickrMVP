package to.marcus.FlickrMVP.modules;

import android.content.Context;
import com.squareup.otto.Bus;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import to.marcus.FlickrMVP.data.PhotoCache;
import to.marcus.FlickrMVP.ui.views.base.BaseApplication;
import to.marcus.FlickrMVP.network.ApiEndpoint;
import to.marcus.FlickrMVP.network.ApiService;

/**
 * A module for Android-specific dependencies which require a {@link Context} or
 * {@link android.app.Application} to create.
 */

@Module(
        injects = BaseApplication.class
       )

public class ApplicationModule {

    private final String apiKey;

    public ApplicationModule(String apiKey) {
        this.apiKey = apiKey;
    }

    @Provides
    @Singleton
    public PhotoCache provideCache(){return new PhotoCache();}

    @Provides
    @Singleton
    public Bus provideBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    public ApiEndpoint provideEndPoint() {
        return new ApiEndpoint();
    }

    @Provides
    @Singleton
    public ApiService provideApiService(ApiEndpoint endpoint){
        return new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
                .create(ApiService.class);
    }
}
