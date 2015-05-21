package to.marcus.FlickrMVP.model;

import com.google.gson.annotations.Expose;

/**
 * Created by marcus on 4/10/2015.
 */

public class ImageResponse {

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
