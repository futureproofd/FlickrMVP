package to.marcus.FlickrMVP.ui.views.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import to.marcus.FlickrMVP.modules.RecentModule;
import to.marcus.FlickrMVP.ui.adapter.HomePagerAdapter;
import to.marcus.FlickrMVP.ui.presenter.RecentPresenter;
import to.marcus.FlickrMVP.ui.views.PhotosView;
import to.marcus.FlickrMVP.ui.views.activity.HomeActivity;
import to.marcus.FlickrMVP.ui.views.base.BaseFragment;
import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.model.Photo;
import to.marcus.FlickrMVP.network.PhotoHandler;
import to.marcus.FlickrMVP.ui.adapter.PhotoAdapter;

/**
 * Created by marcus on 31/03/15
 */

@SuppressLint("ValidFragment")
public class RecentFragment extends BaseFragment implements PhotosView {
    private final String TAG = RecentFragment.class.getSimpleName();
    GridView mGridView;
    ProgressBar mProgressBar;
    SwipeRefreshLayout mSwipeRefreshWidget;
    ArrayList<Photo> receivedPhotosList;
    @Inject RecentPresenter recentPresenter;
    PhotoHandler mResponseHandler;

    //factory
    public static RecentFragment newInstance(HomePagerAdapter.FragmentChangeListener listener){
        return new RecentFragment(listener);
    }

    //Constructors
    public RecentFragment(){}

    public RecentFragment(HomePagerAdapter.FragmentChangeListener listener){
        mListener = listener;
    }

    @Override
    //Get BaseFragment scoped ObjectGraph
    public void onCreate(Bundle savedInstanceState){
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        Log.i(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        mProgressBar = ((HomeActivity)getActivity()).getProgressBar();
        recentPresenter.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewPagerContainer,
                                Bundle savedInstanceState){
        Log.i(TAG, "onCreateView");
        View v = inflater.inflate(R.layout.fragment_grid_layout, viewPagerContainer, false);
        mGridView = (GridView)v.findViewById(R.id.gridView);
        mSwipeRefreshWidget = (SwipeRefreshLayout)v.findViewById(R.id.swipe_refresh_main);
        return v;
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG, "onResume");
        //get bus
        recentPresenter.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i(TAG, "onPause");
        //destroy bus
        recentPresenter.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        //mResponseHandler.quit();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.i(TAG, "onDestroyView");
        //mResponseHandler.clearQueue();
    }

    @Override
    public void onSaveInstanceState(Bundle out){
        super.onSaveInstanceState(out);
        Log.i(TAG, "onSaveInstanceState");
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
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Photo photo =(Photo)mGridView.getAdapter().getItem(position);
                recentPresenter.onNetworkPhotoSelected(photo.getBigUrl());
            }
        });
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
        mSwipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
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
    public void showWebViewPhotoFragment(String url){
        Bundle args = new Bundle();
        args.putString(getContext().getString(R.string.large_photo), url);
        //HomePageAdapter Listener
        if(mListener != null){
            mListener.onSwitchToNextFragment(args);
        }
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
