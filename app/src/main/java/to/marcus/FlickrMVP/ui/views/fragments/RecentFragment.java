package to.marcus.FlickrMVP.ui.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import to.marcus.FlickrMVP.modules.RecentModule;
import to.marcus.FlickrMVP.ui.presenter.RecentPresenter;
import to.marcus.FlickrMVP.ui.views.PhotosView;
import to.marcus.FlickrMVP.ui.views.base.BaseFragment;
import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.model.Photo;
import to.marcus.FlickrMVP.network.PhotoHandler;
import to.marcus.FlickrMVP.ui.adapter.PhotoAdapter;

/**
 * Created by marcus on 31/03/15!
 */

public class RecentFragment extends BaseFragment implements PhotosView {
    private final String TAG = RecentFragment.class.getSimpleName();
    GridView mGridView;
    ArrayList<Photo> receivedPhotosList;
    @Inject
    RecentPresenter recentPresenter;
    PhotoHandler mResponseHandler;

    public static RecentFragment newInstance(){
        return new RecentFragment();
    }

    @Override
    //Get BaseFragment scoped ObjectGraph
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(TAG, "on create");
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "on activity created");
        recentPresenter.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_grid_layout, container, false);
        mGridView = (GridView)v.findViewById(R.id.gridView);
        return v;
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG, "on resume");
        //get bus
        recentPresenter.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i(TAG, "on pause");
        //destroy bus
        recentPresenter.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "on destroy");
        //mResponseHandler.quit();
    }


    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.i(TAG, "on destroy view");
        //mResponseHandler.clearQueue();
    }

    @Override
    public void onSaveInstanceState(Bundle out){
        super.onSaveInstanceState(out);
        recentPresenter.onSaveInstanceState(out);
        Log.i(TAG, "on save instance state");
    }

    //BaseFragment
    @Override
    public List<Object> getModules(){
        return Arrays.<Object>asList(
                new RecentModule(this)
        );
    }

    /**
     * View implementations
    */
    @Override
    public void setGridViewAdapter(PhotoAdapter adapter){
        mGridView.setAdapter(adapter);
    }

    @Override
    public void setPhotos(ArrayList<Photo> images) {
        Log.i(TAG, "array received via Presenter");
        this.receivedPhotosList = images;
    }

    @Override
    public Context getContext(){
        return this.getActivity();
    }

    @Override
    public ArrayList<Photo> getPhotoArray(){
        return receivedPhotosList;
    }

    //showloadingindicator()
        //progressIndicator.setVisibility(Visible)

    //shownoresultsfound
        //showerror

}
