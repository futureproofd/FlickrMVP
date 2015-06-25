package to.marcus.FlickrMVP.data.event;

import android.util.Log;
import java.util.ArrayList;
import to.marcus.FlickrMVP.model.Photo;

/**
 * Created by marcus on 22/04/15.
 */

public class ImagesReceivedEvent {

    private static final String TAG = "ImagesReceivedEvent";
    private ArrayList<Photo> result;

    public ImagesReceivedEvent(ArrayList<Photo> result){
        Log.i(TAG, "arraylist for presenter");
        this.result = result;
    }

    public ArrayList<Photo> getResult(){return result;}
}