package to.marcus.FlickrMVP.modules;

import com.squareup.otto.Bus;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import to.marcus.FlickrMVP.data.PhotoCache;
import to.marcus.FlickrMVP.data.interactor.PhotoInteractor;
import to.marcus.FlickrMVP.ui.presenter.RecentPresenter;
import to.marcus.FlickrMVP.ui.presenter.RecentPresenterImpl;
import to.marcus.FlickrMVP.ui.views.fragments.RecentFragment;
import to.marcus.FlickrMVP.ui.views.PhotosView;


/**
 * Created by marcus on 4/2/2015
 */

@Module(injects = RecentFragment.class,
        addsTo = ApplicationModule.class,
        complete = false
        )

public class RecentModule {
    public static final String TAG = RecentModule.class.getSimpleName();

    private PhotosView photosView;

    public RecentModule(PhotosView photosView){
        this.photosView = photosView;
    }

    @Provides
    public PhotosView provideView(){
        return photosView;
    }

    @Provides @Singleton
    public RecentPresenter provideImagePresenter(PhotosView photosView
                ,Bus bus
                ,PhotoCache photoCache
                ,PhotoInteractor photoInteractor
             ){
            return new RecentPresenterImpl(photosView, bus, photoCache, photoInteractor);
        }
}
