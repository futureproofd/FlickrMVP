package to.marcus.FlickrMVP.ui.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.model.Photo;
import to.marcus.FlickrMVP.modules.SearchModule;
import to.marcus.FlickrMVP.network.PhotoHandler;
import to.marcus.FlickrMVP.ui.adapter.PhotoAdapter;
import to.marcus.FlickrMVP.ui.presenter.SearchPresenter;
import to.marcus.FlickrMVP.ui.views.PhotosView;
import to.marcus.FlickrMVP.ui.views.base.BaseFragment;

/**
 * Created by marcus on 6/26/2015
 */

public class SearchFragment extends BaseFragment implements PhotosView{
    private static final String TAG = SearchFragment.class.getSimpleName();
    GridView mGridView;
    ArrayList<Photo> receivedPhotosList;
    @Inject
    SearchPresenter searchPresenter;
    PhotoHandler mResponseHandler;

    public static SearchFragment newInstance(){return new SearchFragment();}

    @Override
    //Get BaseFragment scoped ObjectGraph
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(TAG, "on create");
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "on activity created");
        searchPresenter.onActivityCreated(savedInstanceState);
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
            case R.id.search_item:
                searchPresenter.requestNetworkPhotos("sexy woman");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG, "on resume");
        //get bus
        searchPresenter.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i(TAG, "on pause");
        //destroy bus
        searchPresenter.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "on destroy");
        //mResponseHandler.quit();
    }

    @Override
    public void onSaveInstanceState(Bundle out){
        super.onSaveInstanceState(out);
        searchPresenter.onSaveInstanceState(out);
        Log.i(TAG, "on save instance state");
    }


    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.i(TAG, "on destroy view");
        //mResponseHandler.clearQueue();
    }

    //BaseFragment
    @Override
    public List<Object> getModules(){
        return Arrays.<Object>asList(
                new SearchModule(this)
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
        Log.i(TAG, "array received via recentPresenter");
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

}
