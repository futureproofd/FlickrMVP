package to.marcus.FlickrMVP.modules;

import com.squareup.otto.Bus;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import to.marcus.FlickrMVP.ui.presenter.ImagePresenter;
import to.marcus.FlickrMVP.ui.presenter.ImagePresenterImpl;
import to.marcus.FlickrMVP.ui.views.HomeFragment;
import to.marcus.FlickrMVP.ui.views.PhotosView;

/**
 * Created by marcus on 4/2/2015
 */

@Module(injects = HomeFragment.class,
        addsTo = ApplicationModule.class,
        complete = false
        )

public class PresenterModule{
    public static final String TAG = PresenterModule.class.getSimpleName();

    private PhotosView photosView;

    public PresenterModule(PhotosView photosView){
        this.photosView = photosView;
    }

    @Provides
    public PhotosView provideView(){
        return photosView;
    }

    @Provides @Singleton
    public ImagePresenter provideImagePresenter(PhotosView photosView, Bus bus){
        return new ImagePresenterImpl(photosView, bus);
    }
}
