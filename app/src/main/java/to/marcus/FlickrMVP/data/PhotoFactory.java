package to.marcus.FlickrMVP.data;

import java.util.ArrayList;

import to.marcus.FlickrMVP.model.Photo;

/**
 * Created by marcys on 6/5/2015
 * Initial Factory to return different types of photo arrays (search term, recent, history)
 */

public class PhotoFactory{
    public static final String TAG = PhotoFactory.class.getSimpleName();

    public static final class Photos{

        public static to.marcus.FlickrMVP.model.Photos initDefaultPhotosArray(){
            final to.marcus.FlickrMVP.model.Photos defaultPhotosArray = new to.marcus.FlickrMVP.model.Photos();
            defaultPhotosArray.setPhotos(new ArrayList<Photo>());
            return defaultPhotosArray;
        }

        public static to.marcus.FlickrMVP.model.Photos initDefaultPhotosHistoryArray(){
            final to.marcus.FlickrMVP.model.Photos defaultPhotosHistoryArray = new to.marcus.FlickrMVP.model.Photos();
            defaultPhotosHistoryArray.setPhotos(new ArrayList<Photo>());
            return defaultPhotosHistoryArray;
        }
    }
}
