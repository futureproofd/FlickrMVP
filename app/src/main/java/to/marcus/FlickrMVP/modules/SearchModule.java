package to.marcus.FlickrMVP.modules;

import com.squareup.otto.Bus;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import to.marcus.FlickrMVP.data.PhotoCache;
import to.marcus.FlickrMVP.ui.presenter.SearchPresenter;
import to.marcus.FlickrMVP.ui.presenter.SearchPresenterImpl;
import to.marcus.FlickrMVP.ui.views.PhotosView;
import to.marcus.FlickrMVP.ui.views.fragments.SearchFragment;

/**
 * Created by marcus on 6/26/2015
 */

@Module(injects = SearchFragment.class,
        addsTo = ApplicationModule.class,
        complete = false
)

public class SearchModule {
    public static final String TAG = SearchModule.class.getSimpleName();

    private PhotosView photosView;

    public SearchModule(PhotosView photosView){
        this.photosView = photosView;
    }

    @Provides
    public PhotosView provideView(){
        return photosView;
    }

    @Provides @Singleton
    public SearchPresenter provideImagePresenter(PhotosView photosView, Bus bus, PhotoCache photoCache){
        return new SearchPresenterImpl(photosView, bus, photoCache);
    }
}
