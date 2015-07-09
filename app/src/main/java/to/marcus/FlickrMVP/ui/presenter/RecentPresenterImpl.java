package to.marcus.FlickrMVP.ui.presenter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.LruCache;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import to.marcus.FlickrMVP.data.PhotoCache;
import to.marcus.FlickrMVP.data.PhotoFactory;
import to.marcus.FlickrMVP.data.event.ImagesReceivedEvent;
import to.marcus.FlickrMVP.data.event.ImagesRequestedEvent;
import to.marcus.FlickrMVP.model.Photos;
import to.marcus.FlickrMVP.network.PhotoHandler;
import to.marcus.FlickrMVP.ui.adapter.PhotoAdapter;
import to.marcus.FlickrMVP.ui.views.PhotosView;

/**
* Created by marcus on 15/04/15
*/

public class RecentPresenterImpl implements RecentPresenter {
    private final String TAG = RecentPresenterImpl.class.getSimpleName();
    private PhotoCache photoCache;
    private final Bus bus;
    private PhotosView view;
    private Photos defaultPhotosArray;
    PhotoHandler mResponseHandler;

    public RecentPresenterImpl(PhotosView view, Bus bus, PhotoCache photoCache){
        this.photoCache = photoCache;
        this.bus = bus;
        this.view = view;
        initResponseHandler();
    }

    //to-do
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        Log.i(TAG, "on activity created");
        if(savedInstanceState == null){
            //view.showprogressbar
            initInstanceState();
            requestNetworkPhotos();
        }else{
            //pullImagesFromCache --need to setup LRU
            restoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle out){
        Log.i(TAG, "on save instance state");
        Photos.putParcelableArray(out, defaultPhotosArray);
    }

    private void restoreInstanceState(Bundle savedInstanceState){
        Log.i(TAG, "on restore instance state");
        defaultPhotosArray = Photos.getParcelableArray(savedInstanceState);
        initGridViewAdapter();
    }

    @Override
    public void onResume(){
        bus.register(this);
    }

    @Override
    public void onPause(){
        bus.unregister(this);
    }

    @Override
    public void onRefresh(){}

    @Override
    public void onDestroy(){}

    @Override
    public void requestNetworkPhotos(){
        //view.showloadingIndicator  -- HomeFragment will implement our view interface and override this method
       // Log.i(TAG, "Images Requested Event!");
        bus.post(new ImagesRequestedEvent());  //this will notify our ApiRequestHandler
    }

    @Subscribe
    public void onImagesArrayReceived(ImagesReceivedEvent event){
        Log.i(TAG, "array ready for presenter");
        this.defaultPhotosArray.setPhotos(event.getResult());
        initGridViewAdapter();
    }

    private void initInstanceState(){
        defaultPhotosArray = PhotoFactory.Photos.initDefaultPhotosArray();
            //get cached image array
            //initGridViewAdapter
        //if not, requestNetworkPhotos();
    }

    private void initResponseHandler(){
        mResponseHandler = new PhotoHandler<android.widget.ImageView>(new Handler(), photoCache);
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

    private void initGridViewAdapter(){
        //to-do : determine which photosArray to use (cached / network?)
        view.setGridViewAdapter(new PhotoAdapter(view.getContext(), defaultPhotosArray.getPhotos(), mResponseHandler));
    }

}