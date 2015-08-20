package to.marcus.FlickrMVP.modules;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import to.marcus.FlickrMVP.data.interactor.PhotoInteractor;
import to.marcus.FlickrMVP.data.interactor.PhotoInteractorImpl;
import to.marcus.FlickrMVP.ui.views.base.BaseApplication;

/**
 * Created by marcus on 8/13/2015
 * Interactor for a provided back-end implementation
 */

@Module(library = true,
        complete = false
        )
public class InteractorModule{

    @Provides
    @Singleton
    public PhotoInteractor providePhotoInteractor(BaseApplication application){
        return new PhotoInteractorImpl(application);
    }
}
