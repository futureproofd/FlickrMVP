package to.marcus.FlickrMVP.data.interactor;

import java.util.ArrayList;

import to.marcus.FlickrMVP.model.Photo;
import to.marcus.FlickrMVP.model.Photos;

/**
 * Created by marcus on 8/13/2015
 */
public interface PhotoInteractor {
    public void addPhoto(Photo photo);
    public void deletePhoto(Photo photo);
    public void getPhoto(Photo photo);
    public ArrayList<Photo> getPhotos();
    public void deletePhotos();
    public void savePhotos();
}
