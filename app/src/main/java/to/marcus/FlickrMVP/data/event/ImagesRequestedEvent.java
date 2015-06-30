package to.marcus.FlickrMVP.data.event;

import android.util.Log;

/**
 * Created by mplienegger on 4/20/2015
 */

public class ImagesRequestedEvent {
    private final static String TAG = ImagesRequestedEvent.class.getSimpleName();
    private String result;

    //GetRecent
    public ImagesRequestedEvent(){
        Log.i(TAG, "GetRecent requested Event");
    };

    public String getResult(){
        return result;
    }
}
