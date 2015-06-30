package to.marcus.FlickrMVP.data.event;

import android.util.Log;

/**
 * Created by marcus on 6/29/2015
 */

public class SearchRequestedEvent {
    private final static String TAG = SearchRequestedEvent.class.getSimpleName();
    private String result;

    //Search
    public SearchRequestedEvent(String result){
        Log.i(TAG, "Search requested Event");
        this.result = result;
    }

    public String getResult(){
        return result;
    }
}
