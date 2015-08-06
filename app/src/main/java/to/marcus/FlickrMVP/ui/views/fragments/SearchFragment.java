package to.marcus.FlickrMVP.ui.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.model.Photo;
import to.marcus.FlickrMVP.modules.SearchModule;
import to.marcus.FlickrMVP.network.PhotoHandler;
import to.marcus.FlickrMVP.ui.adapter.PhotoRecyclerAdapter;
import to.marcus.FlickrMVP.ui.presenter.SearchPresenter;
import to.marcus.FlickrMVP.ui.views.PhotosView;
import to.marcus.FlickrMVP.ui.views.activity.HomeActivity;
import to.marcus.FlickrMVP.ui.views.base.BaseFragment;

/**
 * Created by marcus on 6/26/2015
 */

public class SearchFragment extends BaseFragment implements PhotosView{
    private final String TAG = SearchFragment.class.getSimpleName();
    ProgressBar mProgressBar;
    SwipeRefreshLayout mSwipeRefreshWidget;
    ArrayList<Photo> receivedPhotosList;
    @Inject
    SearchPresenter searchPresenter;
    PhotoHandler mResponseHandler;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;

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
        mRecyclerView = (RecyclerView)v.findViewById(R.id.my_recycler_view);
        mSwipeRefreshWidget = (SwipeRefreshLayout)v.findViewById(R.id.swipe_refresh_main);
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

    //result from Activity
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
    public void setGridViewAdapter(PhotoRecyclerAdapter adapter){
        mLayoutManager = new GridLayoutManager(getContext(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
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
    public void initSwipeRefreshWidget(){
        mSwipeRefreshWidget.setColorSchemeColors(R.color.tabScroll);
        mSwipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchPresenter.onRefresh();
            }
        });
    }

    @Override
    public void showSwipeRefreshWidget() {
        mSwipeRefreshWidget.setRefreshing(true);
    }

    @Override
    public void hideSwipeRefreshWidget() {
        mSwipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public boolean isSwipeRefreshing(){return mSwipeRefreshWidget.isRefreshing();}

    @Override
    public void showWebViewPhotoFragment(String url) {

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

    @Override
    public PhotoRecyclerAdapter getAdapter(){
        return null;
    }

}
