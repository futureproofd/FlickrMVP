package to.marcus.FlickrMVP.ui.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import to.marcus.FlickrMVP.event.ImagesReceivedEvent;
import to.marcus.FlickrMVP.event.ImagesRequestedEvent;
import to.marcus.FlickrMVP.model.Photos.Photo;
import to.marcus.FlickrMVP.network.PhotoHandler;
import to.marcus.FlickrMVP.ui.adapter.PhotoAdapter;

/**
 * Created by marcus on 15/04/15.
 */

public class ImagePresenter{

    //define a View interface
    public interface PhotosView {
        void setPhotos(ArrayList<Photo> images);
        ArrayList<Photo> getPhotoArray();
        void setGridViewAdapter(PhotoAdapter adapter);
        Context getContext();
    }

    private final String TAG = "ImagePresenter";
    private final Bus bus;
    private final PhotosView view;
    PhotoHandler mResponseHandler;

    public ImagePresenter(Bus bus, PhotosView view){
        this.bus = bus;
        this.view = view;
    }

    public void initComponents(){
        initResponseHandler();
        initAdapter();
    }

    public void onResume(){
        bus.register(this);
    }

    public void onPause(){
        bus.unregister(this);
    }

    public void requestImages(String request){
        //view.showloadingIndicator  -- HomeFragment will implement our view interface and override this method
        Log.i(TAG, "Images Requested Event!");
        bus.post(new ImagesRequestedEvent(request));  //this will notify our ApiRequestHandler
    }

    @Subscribe
    public void onImagesArrayReceived(ImagesReceivedEvent event){
        Log.i(TAG, "array ready for presenter");
        view.setPhotos(event.getResult());
    }


    private void initResponseHandler(){
        mResponseHandler = new PhotoHandler<android.widget.ImageView>(new Handler());
        mResponseHandler.start();
        mResponseHandler.getLooper();
        //listen for incoming images sent back to main handler thread
        mResponseHandler.setListener(new PhotoHandler.Listener<android.widget.ImageView>() {
            public void onPhotoDownloaded(android.widget.ImageView imageView, Bitmap thumbnail) {
                imageView.setImageBitmap(thumbnail);
                Log.i(TAG, "ResponseHandler: imageview set thumbnail");
            }
        });
    }

    private void initAdapter(){
        view.setGridViewAdapter(new PhotoAdapter(view.getContext(), view.getPhotoArray(), mResponseHandler));
    }

}
