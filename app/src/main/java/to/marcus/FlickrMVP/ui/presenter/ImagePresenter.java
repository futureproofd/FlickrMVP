package to.marcus.FlickrMVP.ui.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.GridView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;

import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.event.ImagesReceivedEvent;
import to.marcus.FlickrMVP.event.ImagesRequestedEvent;
import to.marcus.FlickrMVP.model.Photos;
import to.marcus.FlickrMVP.model.Photos.Photo;
import to.marcus.FlickrMVP.network.PhotoHandler;
import to.marcus.FlickrMVP.ui.adapter.PhotoAdapter;

/**
 * Created by marcus on 15/04/15.
 */

public class ImagePresenter{

    //define a View interface
    public interface ImageView{
        void getPhotoArray(ArrayList<Photo> images);
        void setGridViewAdapter(PhotoAdapter adapter);
        public Context getContext();
    }

    private final String TAG = "ImagePresenter";
    private final Bus bus;
    private final ImageView view;
    private ArrayList<Photo> mPhotos;
    PhotoHandler mResponseHandler;

    public ImagePresenter(Bus bus, ImageView view){
        this.bus = bus;
        this.view = view;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        Log.i(TAG, "activity created");
        initResponseHandler();
        view.setGridViewAdapter(new PhotoAdapter(view.getContext(), mPhotos, mResponseHandler));
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
        //changed
        mPhotos = event.getResult();
        view.getPhotoArray(mPhotos);
    }


    private void initResponseHandler(){
        //set responseHandler thread here instead of homeFragment
        mResponseHandler = new PhotoHandler<ImageView>(new Handler());
        mResponseHandler.start();
        mResponseHandler.getLooper();
        //listen for incoming images sent back to main handler thread
        mResponseHandler.setListener(new PhotoHandler.Listener<ImageView>() {
            public void onPhotoDownloaded(ImageView imageView, Bitmap thumbnail) {
                //also need to establish the recyclerview or existing gridview in presenter class to set this
                //imageView.setImageBitmap(thumbnail);
                Log.i(TAG, "ResponseHandler: imageview set thumbnail");
            }
        });
    }

}
