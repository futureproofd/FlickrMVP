package to.marcus.FlickrMVP.data.interactor;

import java.util.ArrayList;
import to.marcus.FlickrMVP.model.Photo;

/**
 * Created by marcus on 8/13/2015
 */

public interface PhotoInteractor {
    public void addPhoto(Photo photo);
    public ArrayList<Photo> getPhotos();
    public void deletePhotos();
    public void savePhotos();
}
