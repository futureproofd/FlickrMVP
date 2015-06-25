package to.marcus.FlickrMVP.model;

import com.google.gson.annotations.Expose;

/**
 * Magic GSON To Photos / Photo object converter
 * Links to Photos class which defines the EXACT JSON Fields
 */

public class PhotosResponse {

    @Expose private Photos photos;
    @Expose private String stat;

    public Photos getPhotosObject(){return photos;}

    public void setPhotosObject(Photos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
