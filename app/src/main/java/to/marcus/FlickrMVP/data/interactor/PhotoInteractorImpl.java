package to.marcus.FlickrMVP.data.interactor;

import java.util.ArrayList;
import javax.inject.Inject;
import dagger.ObjectGraph;
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

    public PhotoInteractorImpl(BaseApplication application){
        ObjectGraph objectGraph = ObjectGraphHolder.createScopedObjectGraph(application)
                .plus(PhotoStorageModule.class);
        objectGraph.inject(this);
    }

    @Override
    public void addPhoto(Photo photo){photoStorage.addPhoto(photo);}

    @Override
    public ArrayList<Photo> getPhotos(){return photoStorage.getPhotos();}

    @Override
    public void deletePhotos(){photoStorage.deletePhotos();}

    @Override
    public void savePhotos(){photoStorage.savePhotosToFile();}
}
