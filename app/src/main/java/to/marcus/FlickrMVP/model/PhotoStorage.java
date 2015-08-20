package to.marcus.FlickrMVP.model;

import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import to.marcus.FlickrMVP.data.PhotoJSONer;

/**
 * Created by marcus on 8/14/2015
 */

public class PhotoStorage{
    private ArrayList<Photo> mPhotos;
    private PhotoJSONer mSerializer;
    private static PhotoStorage sPhotoStorage;
    private Context mAppContext;
    private static final String PHOTOS_DATABASE = "photos.json";
    public static final String TAG = PhotoStorage.class.getSimpleName();

    public PhotoStorage(Context mAppContext){
        mSerializer = new PhotoJSONer(mAppContext,PHOTOS_DATABASE);
        try{
            mPhotos = mSerializer.loadPhotos();
        }catch(Exception e){
            mPhotos = new ArrayList<Photo>();
        }
    }

    public ArrayList<Photo> getPhotos(){
        return mPhotos;
    }

    public Photo getPhoto(String url){
        for(Photo p: mPhotos){
            if(p.getUrl() == url)
                return p;
        }
        return null;
    }

    public void deletePhoto(String url){
        Log.i(TAG, "deleted photo");
        mPhotos.remove(url);
    }

    public void deletePhotos(){
        mPhotos.clear();
    }

    public void addPhoto(Photo photo){
        Log.i(TAG, "added photo");
        mPhotos.add(photo);
    }

    public boolean savePhotosToFile(){
        try{
            mSerializer.savePhotos(mPhotos);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
