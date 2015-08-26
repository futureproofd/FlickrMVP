package to.marcus.FlickrMVP.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Photo objects for Photos Array
 */
public class Photo implements Parcelable {
    public static final String TAG = Photos.class.getSimpleName();
    public static final String PARCEL_PHOTO_KEY = TAG + " " + "PARCEL_KEY";

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

    private Photo(String id, String owner, String secret, String server, int farm, String title, int ispublic, int isfriend, int isfamily, String url_s){
        this.id = id;
        this.owner = owner;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
        this.title = title;
        this.ispublic = ispublic;
        this.isfriend = isfriend;
        this.isfamily = isfamily;
        this.url_s = url_s;
    }

    public Photo(JSONObject json) throws JSONException{
        title = json.getString("title");
        url_s = json.getString("url");
    }

    //helper methods:
    @Override
    public String toString(){return title;}
    public String getUrl(){return url_s;}
    public String getTitle(){return title;}
    public String getBigUrl(){
        return url_s.substring(0,((url_s.length()-1)-5))+"_c.jpg";
    }

    /**
     * Parcelable methods
     * to save instance state on fragment start/stop
    */
    public static final Parcelable.Creator<Photo> CREATOR = new Creator<Photo>(){

        @Override
        public Photo createFromParcel(Parcel parcel) {
            return new Photo(parcel);
        }

        @Override
        public Photo[] newArray(int i) {
            return new Photo[i];
        }
    };

    private Photo(Parcel in){
        this(
                in.readString(),
                in.readString(),
                in.readString(),
                in.readString(),
                in.readInt(),
                in.readString(),
                in.readInt(),
                in.readInt(),
                in.readInt(),
                in.readString()
        );
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(id);
        out.writeString(owner);
        out.writeString(secret);
        out.writeString(server);
        out.writeInt(farm);
        out.writeString(title);
        out.writeInt(ispublic);
        out.writeInt(isfriend);
        out.writeInt(isfamily);
        out.writeString(url_s);
    }

    public Photo getParcelable(Bundle savedInstanceState){
        return savedInstanceState.getParcelable(PARCEL_PHOTO_KEY);
    }

    public void putParcelable(Bundle savedInstanceState, Photo photo){
        savedInstanceState.putParcelable(PARCEL_PHOTO_KEY, photo);
    }

}