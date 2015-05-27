package to.marcus.FlickrMVP.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import java.util.ArrayList;
import javax.inject.Inject;
import to.marcus.FlickrMVP.BaseApplication;
import to.marcus.FlickrMVP.BaseFragment;
import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.model.Photos.Photo;
import to.marcus.FlickrMVP.modules.PresenterModule;
import to.marcus.FlickrMVP.network.PhotoHandler;
import to.marcus.FlickrMVP.ui.adapter.PhotoAdapter;
import to.marcus.FlickrMVP.ui.presenter.ImagePresenter;

/**
 * Created by marcus on 31/03/15!
 */

public class HomeFragment extends BaseFragment implements ImagePresenter.ImageView{
    private final String TAG = "HomeFragment";
    GridView mGridView;
    ArrayList<Photo> mImages;
    @Inject ImagePresenter presenter;
    PhotoHandler mResponseHandler;

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        BaseApplication.get(getActivity())
                .createScopedGraph(new PresenterModule(this))
                .inject(this);
        setHasOptionsMenu(true);
        presenter.onImagesRequested("test");
        //setup a new thread (main thread) to communicate with the background thread
        /*
        mResponseHandler = new PhotoHandler<ImageView>(new Handler());
        mResponseHandler.start();
        mResponseHandler.getLooper();
        //listen for incoming images sent back to main handler thread
        mResponseHandler.setListener(new PhotoHandler.Listener<ImageView>() {
            public void onPhotoDownloaded(ImageView imageView, Bitmap thumbnail) {
                imageView.setImageBitmap(thumbnail);
            }
        });
        */
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        Log.i(TAG, "activity created");
        super.onActivityCreated(savedInstanceState);
        presenter.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_grid_layout, container, false);
        mGridView = (GridView)v.findViewById(R.id.gridView);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.action, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.refresh:
                Log.i(TAG, "clicked to get images!");
                presenter.onImagesRequested("test");
                return true;
        }
        Log.i(TAG, "didn't get shit");
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        super.onResume();
        //get bus
        presenter.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mResponseHandler.quit();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        mResponseHandler.clearQueue();
    }

    //View stuff

    @Override
    public void setGridViewAdapter(PhotoAdapter adapter){
        //if(getActivity() == null || mGridView == null) return;
       // if (mImages != null){
             mGridView.setAdapter(adapter);
            //mGridView.setAdapter(new PhotoAdapter(getActivity(), mImages, mResponseHandler));
      //  }else{
           // mGridView.setAdapter(null);
      //  }
    }

    @Override
    public void getPhotoArray(ArrayList<Photo> images) {
        Log.i(TAG, "array received via presenter");
        this.mImages = images;
        //setAdapter();
    }

    @Override
    public Context getContext(){
        return this.getActivity();
    }
    //showloadingindicator()
        //progressIndicator.setVisibility(Visible)

    //shownoresultsfound
        //showerror

}
