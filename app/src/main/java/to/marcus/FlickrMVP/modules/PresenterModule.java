package to.marcus.FlickrMVP.modules;

import com.squareup.otto.Bus;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import to.marcus.FlickrMVP.ui.presenter.ImagePresenter;
import to.marcus.FlickrMVP.ui.HomeFragment;

/**
 * Created by marcus on 4/2/2015
 */

@Module(injects = HomeFragment.class,
        addsTo = ApplicationModule.class,
        complete = false
)
public class PresenterModule {

    private final ImagePresenter.ImageView imageView;

    public PresenterModule(ImagePresenter.ImageView imageView){
        this.imageView = imageView;
    }

    @Provides @Singleton
    ImagePresenter provideImagePresenter(Bus bus){
        return new ImagePresenter(bus, imageView);
    }
}
