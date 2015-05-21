package to.marcus.FlickrMVP.event;

import android.util.Log;

/**
 * Created by marcus on 22/04/15.
 */

public class ImagesFailedEvent {

    private static final String TAG = "httpFailedEvent";
    private String result;

    public ImagesFailedEvent(String result){
        Log.i(TAG, "Here's the result");
        this.result = result;
    }

    public String getResult(){return result;}
}
