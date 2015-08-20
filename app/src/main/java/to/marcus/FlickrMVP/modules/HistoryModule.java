package to.marcus.FlickrMVP.modules;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import to.marcus.FlickrMVP.data.PhotoCache;
import to.marcus.FlickrMVP.data.interactor.PhotoInteractor;
import to.marcus.FlickrMVP.ui.presenter.HistoryPresenter;
import to.marcus.FlickrMVP.ui.presenter.HistoryPresenterImpl;
import to.marcus.FlickrMVP.ui.views.PhotosView;
import to.marcus.FlickrMVP.ui.views.fragments.HistoryFragment;

/**
 * Created by marcus on 6/26/2015
 */

@Module(injects = HistoryFragment.class,
        addsTo = ApplicationModule.class,
        complete = false
)

public class HistoryModule{

    private PhotosView photosView;

    public HistoryModule(PhotosView photosView){
        this.photosView = photosView;
    }

    @Provides
    public PhotosView provideView(){
        return photosView;
    }

    @Provides @Singleton
    public HistoryPresenter provideHistoryPresenter(PhotosView photosView, PhotoInteractor photoInteractor, PhotoCache photoCache){
        return new HistoryPresenterImpl(photosView, photoInteractor, photoCache);
    }
}
