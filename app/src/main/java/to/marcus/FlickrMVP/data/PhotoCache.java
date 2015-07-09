package to.marcus.FlickrMVP.data;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by marcus on 7/8/2015
 * Generic LRU cache with helper methods for loading Photo objects
 */

public class PhotoCache{

    public PhotoCache(){
        this.mPhotoCache = new LruCache<String, Bitmap>(cacheSize);
    }

    //Get max available memory, stored in KB
    private final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    private final int cacheSize = maxMemory / 16;

    private LruCache<String, Bitmap> mPhotoCache;

    public void addBitmapToCache(String key, Bitmap bitmap){
        if(getBitmapFromCache(key) == null){
            mPhotoCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromCache(String key){
        return mPhotoCache.get(key);
    }
}
