package to.marcus.FlickrMVP.ui.presenter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import to.marcus.FlickrMVP.data.PhotoCache;
import to.marcus.FlickrMVP.data.PhotoFactory;
import to.marcus.FlickrMVP.data.event.SearchReceivedEvent;
import to.marcus.FlickrMVP.data.event.SearchRequestedEvent;
import to.marcus.FlickrMVP.data.interactor.PhotoInteractor;
import to.marcus.FlickrMVP.data.interactor.PhotoInteractorImpl;
import to.marcus.FlickrMVP.model.Photo;
import to.marcus.FlickrMVP.model.Photos;
import to.marcus.FlickrMVP.network.PhotoHandler;
import to.marcus.FlickrMVP.ui.adapter.PhotoRecyclerAdapter;
import to.marcus.FlickrMVP.ui.views.PhotosView;
import to.marcus.FlickrMVP.ui.views.activity.HomeActivity;

/**
 * Created by marcus on 6/26/2015
 */

public class SearchPresenterImpl implements SearchPresenter {
    private final String TAG = SearchPresenterImpl.class.getSimpleName();
    private String mSearchQuery;
    private PhotoCache photoCache;
    private final Bus bus;
    private PhotosView view;
    private Photos defaultPhotosArray;
    PhotoHandler mResponseHandler;
    private PhotoInteractor photoInteractor;

    public SearchPresenterImpl(PhotosView view, Bus bus, PhotoCache photoCache, PhotoInteractor photoInteractor){
        this.photoCache = photoCache;
        this.bus = bus;
        this.view = view;
        this.photoInteractor = photoInteractor;
        initResponseHandler();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        if(savedInstanceState == null){
            view.showKeyboard();
            initInstanceState();
            initGridViewAdapter();
        }else{
            restoreInstanceState(savedInstanceState);
           // view.hideKeyboard();
        }
        view.initSwipeRefreshWidget();
    }

    @Override
    public void onSaveInstanceState(Bundle out) {
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
    public void onDestroy(){}

    @Override
    public void onRefresh(){
        requestNetworkPhotos(mSearchQuery);
    }

    @Override
    public void requestNetworkPhotos(String query){
        mSearchQuery = query;
        if(!view.isSwipeRefreshing()){
            view.showProgressBar();
        }
        bus.post(new SearchRequestedEvent(query));
    }

    @Override
    public void onNetworkPhotoSelected(String url){view.showWebViewPhotoFragment(url);}

    @Subscribe
    public void onImagesArrayReceived(SearchReceivedEvent event){
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
        mResponseHandler.setListener(new PhotoHandler.Listener<android.widget.ImageView>() {
            public void onPhotoDownloaded(android.widget.ImageView imageView, Bitmap thumbnail) {
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
                    public void onItemClick(View v, int position){
                        Photo photo = view.getAdapter().getItem(position);
                        photoInteractor.addPhoto(photo);
                        onNetworkPhotoSelected(photo.getBigUrl());
                    }
        }));
    }
}
