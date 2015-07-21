package to.marcus.FlickrMVP.ui.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import to.marcus.FlickrMVP.data.PhotoCache;
import to.marcus.FlickrMVP.modules.RecentModule;
import to.marcus.FlickrMVP.ui.presenter.RecentPresenter;
import to.marcus.FlickrMVP.ui.views.PhotosView;
import to.marcus.FlickrMVP.ui.views.activity.HomeActivity;
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
    ProgressBar mProgressBar;
    SwipeRefreshLayout mSwipeRefreshWidget;
    ArrayList<Photo> receivedPhotosList;
    @Inject RecentPresenter recentPresenter;
    PhotoHandler mResponseHandler;

    public static RecentFragment newInstance(){return new RecentFragment();}

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
        recentPresenter.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_grid_layout, container, false);
        mGridView = (GridView)v.findViewById(R.id.gridView);
        mSwipeRefreshWidget = (SwipeRefreshLayout)v.findViewById(R.id.swipe_refresh_main);
        return v;
    }

    @Override
    public void onResume(){
        super.onResume();
        //get bus
        recentPresenter.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        //destroy bus
        recentPresenter.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //mResponseHandler.quit();
    }


    @Override
    public void onDestroyView(){
        super.onDestroyView();
        //mResponseHandler.clearQueue();
    }

    @Override
    public void onSaveInstanceState(Bundle out){
        super.onSaveInstanceState(out);
        recentPresenter.onSaveInstanceState(out);
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
                recentPresenter.onRefresh();
            }
        });
    }

    @Override
    public void showSwipeRefreshWidget(){mSwipeRefreshWidget.setRefreshing(true);}

    @Override
    public void hideSwipeRefreshWidget(){mSwipeRefreshWidget.setRefreshing(false);}

    @Override
    public boolean isSwipeRefreshing(){return mSwipeRefreshWidget.isRefreshing();}

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
