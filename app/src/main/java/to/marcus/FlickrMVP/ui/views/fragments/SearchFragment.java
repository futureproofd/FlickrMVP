package to.marcus.FlickrMVP.ui.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
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
import to.marcus.FlickrMVP.ui.views.activity.HomeActivity;
import to.marcus.FlickrMVP.ui.views.base.BaseFragment;

/**
 * Created by marcus on 6/26/2015
 */

public class SearchFragment extends BaseFragment implements PhotosView{
    private  final String TAG = SearchFragment.class.getSimpleName();
    ProgressBar mProgressBar;
    GridView mGridView;
    ArrayList<Photo> receivedPhotosList;
    @Inject
    SearchPresenter searchPresenter;
    PhotoHandler mResponseHandler;

    public static SearchFragment newInstance(){
        return new SearchFragment();
    }

    @Override
    //Get BaseFragment scoped ObjectGraph
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mProgressBar = ((HomeActivity)getActivity()).getProgressBar();
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
    public void onResume(){
        super.onResume();
        //get bus
        searchPresenter.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        //destroy bus
        searchPresenter.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //mResponseHandler.quit();
    }

    @Override
    public void onSaveInstanceState(Bundle out){
        super.onSaveInstanceState(out);
        searchPresenter.onSaveInstanceState(out);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        //mResponseHandler.clearQueue();
    }

    public void onSearchReceived(String searchTerm){
        searchPresenter.requestNetworkPhotos(searchTerm);
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
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setPhotos(ArrayList<Photo> images) {
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
