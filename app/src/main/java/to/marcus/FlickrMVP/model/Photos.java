package to.marcus.FlickrMVP.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;

/**
 *  * A Class template to hold the values of the GSON deserialization
 *  (variable names must match API!)
 */

public class Photos implements Parcelable {
    public static final String TAG = Photos.class.getSimpleName();
    public static final String PARCEL_PHOTOARRAY_KEY = TAG + " " + "PARCEL_KEY";

    public Photos(){}

    private Photos(ArrayList<Photo> photo){
        this.photo = photo;
    }

    @Expose private int page;
    @Expose private int pages;
    @Expose private int perpage;
    @Expose private String total;
    @Expose private ArrayList<Photo> photo = new ArrayList<Photo>();

    //helpers
    public ArrayList<Photo> getPhotos(){return photo;}
    public void setPhotos(ArrayList<Photo> photos){this.photo = photos;}
    public void addPhoto(Photo photoObj){
        photo.add(photoObj);
    }

    /**
     * Parcelable methods
     * to save instance state on fragment start/stop
     */
    public static final Creator<Photos> CREATOR = new Parcelable.Creator<Photos>(){
        @Override
        public Photos createFromParcel(Parcel in) {
            return new Photos(in);
        }

        @Override
        public Photos[] newArray(int i) {
            return new Photos[i];
        }
    };

    //Parcel constructor,
    private Photos(Parcel in){
        this(new ArrayList<Photo>());
        in.readTypedList(photo, Photo.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i){
        out.writeTypedList(photo);
    }

    public static void putParcelableArray(Bundle savedInstanceState, Photos photos){
        savedInstanceState.putParcelable(PARCEL_PHOTOARRAY_KEY, photos);
    }

    public static Photos getParcelableArray(Bundle savedInstanceState){
        return savedInstanceState.getParcelable(PARCEL_PHOTOARRAY_KEY);
    }

}
