package to.marcus.FlickrMVP.ui.views.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
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
import to.marcus.FlickrMVP.modules.RecentModule;
import to.marcus.FlickrMVP.ui.adapter.PhotoRecyclerAdapter;
import to.marcus.FlickrMVP.ui.presenter.RecentPresenter;
import to.marcus.FlickrMVP.ui.views.PhotosView;
import to.marcus.FlickrMVP.ui.views.activity.HomeActivity;
import to.marcus.FlickrMVP.ui.views.activity.PhotoViewActivity;
import to.marcus.FlickrMVP.ui.views.base.BaseFragment;
import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.model.Photo;

/**
 * Created by marcus on 31/03/15
 */

@SuppressLint("ValidFragment")
public class RecentFragment extends BaseFragment implements PhotosView {
    private final String TAG = RecentFragment.class.getSimpleName();
    ProgressBar mProgressBar;
    SwipeRefreshLayout mSwipeRefreshWidget;
    ArrayList<Photo> receivedPhotosList;
    @Inject RecentPresenter recentPresenter;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    PhotoRecyclerAdapter mRecyclerAdapter;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup viewPagerContainer,
                                Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_grid_layout, viewPagerContainer, false);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
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
        //save photos, destroy bus
        recentPresenter.onPause();
    }

    @Override
    public void onDestroy(){
        recentPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onDestroyView(){
        recentPresenter.onDestroyView();
        super.onDestroyView();
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
    public void setGridViewAdapter(PhotoRecyclerAdapter adapter){
        mRecyclerView.setAdapter(adapter);
        mRecyclerAdapter = adapter;
        mRecyclerView.setItemViewCacheSize(6);
        mLayoutManager = new GridLayoutManager(getContext(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void showProgressBar(){mProgressBar.setVisibility(View.VISIBLE);}

    @Override
    public void hideProgressBar(){mProgressBar.setVisibility(View.GONE);}

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
    public void showKeyboard(){}

    @Override
    public void hideKeyboard(){}

    @Override
    public void showWebViewPhotoFragment(String url){
        Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
        intent.putExtra(getString(R.string.large_photo), url);
        startActivityForResult(intent, 0);
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
        return mRecyclerAdapter;
    }

}