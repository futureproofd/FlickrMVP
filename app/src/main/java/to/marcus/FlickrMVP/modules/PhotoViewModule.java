package to.marcus.FlickrMVP.modules;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import to.marcus.FlickrMVP.data.PhotoCache;
import to.marcus.FlickrMVP.ui.presenter.PhotoViewPresenter;
import to.marcus.FlickrMVP.ui.presenter.PhotoViewPresenterImpl;
import to.marcus.FlickrMVP.ui.views.PhotoLargeView;
import to.marcus.FlickrMVP.ui.views.fragments.PhotoViewFragment;

/**
 * Created by marcus on 7/23/2015.
 */

@Module(injects = PhotoViewFragment.class,
        addsTo = ApplicationModule.class,
        complete = false
)

public class PhotoViewModule {
    public static final String TAG = PhotoViewModule.class.getSimpleName();

    private PhotoLargeView photoLargeView;

    public PhotoViewModule(PhotoLargeView photoLargeView){
        this.photoLargeView = photoLargeView;
    }

    @Provides
    public PhotoLargeView provideView(){
        return photoLargeView;
    }

    @Provides @Singleton
    public PhotoViewPresenter provideWebViewPresenter(PhotoLargeView photoLargeView,PhotoCache photoCache){
        return new PhotoViewPresenterImpl(photoLargeView, photoCache);
    }
}
