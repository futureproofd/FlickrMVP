package to.marcus.FlickrMVP.data.event;

import android.util.Log;

import java.util.ArrayList;

import to.marcus.FlickrMVP.model.Photo;

/**
 * Created by marcus on 6/29/2015.
 */
public class SearchReceivedEvent {

    private static final String TAG = SearchReceivedEvent.class.getSimpleName();
    private ArrayList<Photo> result;

    public SearchReceivedEvent(ArrayList<Photo> result){
        Log.i(TAG, "arraylist for presenter");
        this.result = result;
    }

    public ArrayList<Photo> getResult(){return result;}
}
