package to.marcus.FlickrMVP.modules;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import to.marcus.FlickrMVP.model.PhotoStorage;
import to.marcus.FlickrMVP.data.interactor.PhotoInteractorImpl;
import to.marcus.FlickrMVP.ui.views.base.BaseApplication;

/**
 * Created by marcus on 8/13/2015
 * Back-end injection: PhotoStorage uses a serialization process
 * to store a local JSON file. PhotoInteractor class can interact with
 * any other injected data store.
 */

@Module(injects = PhotoInteractorImpl.class
        ,addsTo = ApplicationModule.class)

public class PhotoStorageModule{

    @Provides
    @Singleton
    public PhotoStorage providePhotoStorage(BaseApplication application){
        return new PhotoStorage(application.getApplicationContext());
    }
}
