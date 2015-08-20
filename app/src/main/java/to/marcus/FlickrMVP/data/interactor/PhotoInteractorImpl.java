package to.marcus.FlickrMVP.data.interactor;

import android.content.Context;

import java.util.ArrayList;
import javax.inject.Inject;
import dagger.ObjectGraph;
import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.model.PhotoStorage;
import to.marcus.FlickrMVP.model.Photo;
import to.marcus.FlickrMVP.modules.PhotoStorageModule;
import to.marcus.FlickrMVP.ui.views.base.BaseApplication;
import to.marcus.FlickrMVP.modules.objectgraph.ObjectGraphHolder;

/**
 * Created by marcus on 8/13/2015
 */

public class PhotoInteractorImpl implements PhotoInteractor{
    @Inject
    PhotoStorage photoStorage;
    @Inject
    Context mAppContext;

    public PhotoInteractorImpl(BaseApplication application){
        ObjectGraph objectGraph = ObjectGraphHolder.createScopedObjectGraph(application)
                .plus(PhotoStorageModule.class);
        objectGraph.inject(this);
    }

    @Override
    public void addPhoto(Photo photo){
        //use application context for photoStorage
        mAppContext.getText(R.string.large_photo);
        photoStorage.addPhoto(photo);
    }

    @Override
    public void deletePhoto(Photo photo) {

    }

    @Override
    public void getPhoto(Photo photo) {

    }

    @Override
    public ArrayList<Photo> getPhotos(){
       // return PhotoStorage.get(mAppContext).getPhotos();
        return photoStorage.getPhotos();
    }

    @Override
    public void deletePhotos() {
        photoStorage.deletePhotos();
    }

    @Override
    public void savePhotos(){
        //PhotoStorage.get(mAppContext).savePhotosToFile();
        photoStorage.savePhotosToFile();
    }
}
