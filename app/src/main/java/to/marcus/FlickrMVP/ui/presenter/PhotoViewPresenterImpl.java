package to.marcus.FlickrMVP.ui.presenter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import to.marcus.FlickrMVP.data.PhotoCache;
import to.marcus.FlickrMVP.network.PhotoHandler;
import to.marcus.FlickrMVP.ui.views.PhotoLargeView;

/**
 * Created by marcus on 7/23/2015
 */

public class PhotoViewPresenterImpl implements PhotoViewPresenter {
    private final String TAG = RecentPresenterImpl.class.getSimpleName();
    private PhotoCache photoCache;
    private PhotoLargeView view;
    PhotoHandler mResponseHandler;

    public PhotoViewPresenterImpl(PhotoLargeView photoLargeView, PhotoCache photoCache){
        this.view = photoLargeView;
        this.photoCache = photoCache;
        initResponseHandler();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        view.toggleFullScreen();
        if(savedInstanceState == null){
            //initInstanceState();
        }else{
           // restoreInstanceState(savedInstanceState);
        }
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
    public void onDestroy(){

    }

    @Override
    public void onRefresh(){

    }

    @Override
    public void requestNetworkPhoto(String url){
        view.showProgressBar();
        mResponseHandler.queueThumbnail(view.getPhotoView(), url);
    }

    private void initResponseHandler(){
        mResponseHandler = new PhotoHandler<ImageView>(new Handler(), photoCache);
        mResponseHandler.start();
        mResponseHandler.getLooper();
        //listen for incoming images sent back to main handler thread
        mResponseHandler.setListener(new PhotoHandler.Listener<android.widget.ImageView>(){
            public void onPhotoDownloaded(android.widget.ImageView imageView, Bitmap LargeBitmap){
                view.hideProgressBar();
                imageView.setImageBitmap(LargeBitmap);
            }
        });
    }
}
