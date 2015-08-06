package to.marcus.FlickrMVP.ui.presenter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import to.marcus.FlickrMVP.data.PhotoCache;
import to.marcus.FlickrMVP.data.PhotoFactory;
import to.marcus.FlickrMVP.data.event.ImagesReceivedEvent;
import to.marcus.FlickrMVP.data.event.ImagesRequestedEvent;
import to.marcus.FlickrMVP.model.Photo;
import to.marcus.FlickrMVP.model.Photos;
import to.marcus.FlickrMVP.network.PhotoHandler;
import to.marcus.FlickrMVP.ui.adapter.PhotoRecyclerAdapter;
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        if(savedInstanceState == null){
            initInstanceState();
            requestNetworkPhotos();
            view.showProgressBar();
        }else{
            restoreInstanceState(savedInstanceState);
        }
        view.initSwipeRefreshWidget();
    }

    @Override
    public void onSaveInstanceState(Bundle out){
        Photos.putParcelableArray(out, defaultPhotosArray);
    }

    private void restoreInstanceState(Bundle savedInstanceState){
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
    public void onRefresh(){
        requestNetworkPhotos();
    }

    @Override
    public void onDestroy(){}

    @Override
    public void requestNetworkPhotos(){
        //notify our ApiRequestHandler
        bus.post(new ImagesRequestedEvent());
    }

    @Override
    public void onNetworkPhotoSelected(String url){
        view.showWebViewPhotoFragment(url);
    }

    @Subscribe
    public void onImagesArrayReceived(ImagesReceivedEvent event){
        this.defaultPhotosArray.setPhotos(event.getResult());
        initGridViewAdapter();
        if(view.isSwipeRefreshing()){
            view.hideSwipeRefreshWidget();
        }
        view.hideProgressBar();
    }

    private void initInstanceState(){
        defaultPhotosArray = PhotoFactory.Photos.initDefaultPhotosArray();
    }

    private void initResponseHandler(){
        mResponseHandler = new PhotoHandler<android.widget.ImageView>(new Handler(), photoCache);
        mResponseHandler.start();
        mResponseHandler.getLooper();
        //listen for incoming images sent back to main handler thread
        mResponseHandler.setListener(new PhotoHandler.Listener<android.widget.ImageView>(){
            public void onPhotoDownloaded(android.widget.ImageView imageView, Bitmap thumbnail){
                imageView.setImageBitmap(thumbnail);
            }
        });
    }

    private void initGridViewAdapter(){
        view.setGridViewAdapter(
            new PhotoRecyclerAdapter(view.getContext()
            ,defaultPhotosArray.getPhotos()
            ,mResponseHandler
            ,new PhotoRecyclerAdapter.RecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Photo photo = view.getAdapter().getItem(position);
                    onNetworkPhotoSelected(photo.getBigUrl());
                }
            })
        );
    }

}