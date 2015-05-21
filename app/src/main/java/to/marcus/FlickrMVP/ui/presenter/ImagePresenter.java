package to.marcus.FlickrMVP.ui.presenter;

import android.util.Log;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import to.marcus.FlickrMVP.event.ImagesReceivedEvent;
import to.marcus.FlickrMVP.event.ImagesRequestedEvent;
import to.marcus.FlickrMVP.model.Photos.Photo;

/**
 * Created by marcus on 15/04/15
 */

public class ImagePresenter{

    //define a View interface
    public interface ImageView{
        void getPhotoArray(ArrayList<Photo> images);
    }

    private final String TAG = "ImagePresenter";
    private final Bus bus;
    private final ImageView view;

    public ImagePresenter(Bus bus, ImageView view){
        this.bus = bus;
        this.view = view;
    }

    public void onResume(){
        bus.register(this);
    }

    public void onPause(){
        bus.unregister(this);
    }

    public void onImagesRequested(String request){
        //view.showloadingIndicator  -- HomeFragment will implement our view interface and override this method
        Log.i(TAG, "Images Requested Event!");
        bus.post(new ImagesRequestedEvent(request));  //this will notify our ApiRequestHandler
    }

    @Subscribe
    public void onImagesArrayReceived(ImagesReceivedEvent event){
        Log.i(TAG, "array ready for presenter");
        view.getPhotoArray(event.getResult());
    }

}
