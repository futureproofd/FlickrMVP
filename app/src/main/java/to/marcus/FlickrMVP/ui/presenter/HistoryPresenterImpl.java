package to.marcus.FlickrMVP.ui.presenter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import to.marcus.FlickrMVP.data.PhotoCache;
import to.marcus.FlickrMVP.data.PhotoFactory;
import to.marcus.FlickrMVP.data.interactor.PhotoInteractor;
import to.marcus.FlickrMVP.model.Photo;
import to.marcus.FlickrMVP.model.Photos;
import to.marcus.FlickrMVP.network.PhotoHandler;
import to.marcus.FlickrMVP.ui.adapter.PhotoRecyclerAdapter;
import to.marcus.FlickrMVP.ui.views.PhotosView;

/**
 * Created by marcus on 8/11/2015
 */

public class HistoryPresenterImpl implements HistoryPresenter{
    private PhotosView view;
    private Photos defaultPhotosHistoryArray;
    private PhotoInteractor photoInteractor;
    public ArrayList<Photo> mPhotos;
    PhotoHandler mResponseHandler;
    private PhotoCache photoCache;

    public HistoryPresenterImpl(PhotosView view, PhotoInteractor photoInteractor, PhotoCache photoCache){
        this.view = view;
        this.photoInteractor = photoInteractor;
        this.photoCache = photoCache;
        initResponseHandler();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        view.showProgressBar();
        mPhotos = photoInteractor.getPhotos();
        initGridViewAdapter();
    }

    private void initResponseHandler(){
        mResponseHandler = new PhotoHandler<ImageView>(new Handler(), photoCache);
        mResponseHandler.start();
        mResponseHandler.getLooper();
        //listen for incoming images sent back to main handler thread
        mResponseHandler.setListener(new PhotoHandler.Listener<android.widget.ImageView>(){
            public void onPhotoDownloaded(android.widget.ImageView imageView, Bitmap thumbnail){
                imageView.setImageBitmap(thumbnail);
            }
        });
    }

    @Override
    public void onNetworkPhotoSelected(String url){
        view.showWebViewPhotoFragment(url);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onDeletePhotos(){
        if(mPhotos.size() == 0){
            Toast.makeText(view.getContext(), "No Photos to delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(view.getContext(), "Photos Deleted", Toast.LENGTH_SHORT).show();
            photoInteractor.deletePhotos();
        }
    }

    private void initInstanceState(){
        defaultPhotosHistoryArray = PhotoFactory.Photos.initDefaultPhotosHistoryArray();
    }

    private void initGridViewAdapter(){
        view.setGridViewAdapter(
            new PhotoRecyclerAdapter(view.getContext()
                ,mPhotos
                ,mResponseHandler
                ,new PhotoRecyclerAdapter.RecyclerViewItemClickListener(){
                    @Override
                    public void onItemClick(View v, int position){
                        Photo photo = view.getAdapter().getItem(position);
                        photoInteractor.addPhoto(photo);
                        onNetworkPhotoSelected(photo.getBigUrl());
                    }
                }
            )
        );
    }
}
