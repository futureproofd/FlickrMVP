package to.marcus.FlickrMVP.data;

import java.util.ArrayList;

/**
 * Created by marcys on 6/5/2015
 * Initial Factory to return different types of photo arrays (key term, top 100?)
 */

public class PhotoFactory{
    public static final String TAG = PhotoFactory.class.getSimpleName();

    public static final class Photos{

        public static to.marcus.FlickrMVP.model.Photos initDefaultPhotosArray(){

            final to.marcus.FlickrMVP.model.Photos defaultPhotosArray = new to.marcus.FlickrMVP.model.Photos();
            defaultPhotosArray.setPhotos(new ArrayList<to.marcus.FlickrMVP.model.Photo>());
            return defaultPhotosArray;
        }
    }
}
