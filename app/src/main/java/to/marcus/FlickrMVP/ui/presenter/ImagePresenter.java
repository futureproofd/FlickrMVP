package to.marcus.FlickrMVP.ui.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import to.marcus.FlickrMVP.data.PhotoFactory;
import to.marcus.FlickrMVP.data.event.ImagesReceivedEvent;
import to.marcus.FlickrMVP.data.event.ImagesRequestedEvent;
import to.marcus.FlickrMVP.model.Photos;
import to.marcus.FlickrMVP.model.Photos.Photo;
import to.marcus.FlickrMVP.network.PhotoHandler;
import to.marcus.FlickrMVP.ui.adapter.PhotoAdapter;

/**
* Created by marcus on 15/04/15
*/

public class ImagePresenter{
    private final String TAG = ImagePresenter.class.getSimpleName();
    private final Bus bus;
    private final PhotosView view;
    private Photos defaultPhotosArray;
    PhotoHandler mResponseHandler;

    /*
    * define a View interface - should move this into it's own view interface class
    */
    public interface PhotosView {
        void setPhotos(ArrayList<Photo> images);
        public ArrayList<Photo> getPhotoArray();
        void setGridViewAdapter(PhotoAdapter adapter);
        public Context getContext();
    }

    public ImagePresenter(Bus bus, PhotosView view){
        this.bus = bus;
        this.view = view;
        initResponseHandler();
    }

    //to-do
    public void onActivityCreated(Bundle savedInstanceState){
        if(savedInstanceState == null){
            //view.showprogressbar
            initInstanceState();
            requestImages("search term");
        }else{
            //getInstanceState
            //pullImagesFromCache --need to setup LRU
        }
    }

    private void initInstanceState(){
         defaultPhotosArray = PhotoFactory.Photos.initDefaultPhotosArray();
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
        this.defaultPhotosArray.setPhotos(event.getResult());
        initAdapter();
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
        //to-do : determine which photosArray to use (cached / network?)
        view.setGridViewAdapter(new PhotoAdapter(view.getContext(), defaultPhotosArray.getPhotos(), mResponseHandler));
    }

}