package to.marcus.FlickrMVP.model;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;

/**
 * Created by marcus on 27/04/15
 * A Class template to hold the values of the GSON deserialization
 * Inner Photo class holds the JSON nested list element from the API call
 * (variable names must match API!)
 */

public class Photos {
    @Expose private int page;
    @Expose private int pages;
    @Expose private int perpage;
    @Expose private String total;
    @Expose private ArrayList<Photo> photo = new ArrayList<Photo>();
    //helpers
    public ArrayList<Photo> getPhotos(){return photo;}
    public void setPhotos(ArrayList<Photo> photos){this.photo = photos;}

    public class Photo {
        //helpers
        @Override
        public String toString(){
            return title;
        }
        public String getUrl(){return url_s;}
        //GSON fields
        @Expose private String id;
        @Expose private String owner;
        @Expose private String secret;
        @Expose private String server;
        @Expose private int farm;
        @Expose private String title;
        @Expose private int ispublic;
        @Expose private int isfriend;
        @Expose private int isfamily;
        @Expose private String url_s;
    }
}
