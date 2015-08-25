package to.marcus.FlickrMVP.ui.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.model.Photo;
import to.marcus.FlickrMVP.modules.HistoryModule;
import to.marcus.FlickrMVP.ui.adapter.PhotoRecyclerAdapter;
import to.marcus.FlickrMVP.ui.presenter.HistoryPresenter;
import to.marcus.FlickrMVP.ui.views.PhotosView;
import to.marcus.FlickrMVP.ui.views.activity.HomeActivity;
import to.marcus.FlickrMVP.ui.views.activity.PhotoViewActivity;
import to.marcus.FlickrMVP.ui.views.base.BaseFragment;

/**
 * Created by marcus on 7/29/2015
 */

public class HistoryFragment extends BaseFragment implements PhotosView{
    private final String TAG = HistoryFragment.class.getSimpleName();
    ProgressBar mProgressBar;
    @Inject
    HistoryPresenter historyPresenter;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    PhotoRecyclerAdapter mRecyclerAdapter;
    ArrayList<Photo> receivedPhotosList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mProgressBar = ((HomeActivity)getActivity()).getProgressBar();
        historyPresenter.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_grid_layout, container, false);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.my_recycler_view);
        return v;
    }

    @Override
    public void onPause(){
        super.onPause();
        historyPresenter.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
        mRecyclerAdapter.notifyDataSetChanged();
    }

    //Result from Activity
    public void deletePhotos(){
        historyPresenter.onDeletePhotos();
        mRecyclerAdapter.notifyDataSetChanged();
    }

    //BaseFragment
    @Override
    public List<Object> getModules(){
        return Arrays.<Object>asList(
                new HistoryModule(this)
        );
    }

    /*
    View Implementations
     */
    @Override
    public void setPhotos(ArrayList<Photo> images){
    }

    @Override
    public ArrayList<Photo> getPhotoArray() {
       return this.receivedPhotosList;
    }

    @Override
    public void setGridViewAdapter(PhotoRecyclerAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
        mRecyclerAdapter = adapter;
        mRecyclerView.setItemViewCacheSize(6);
        mLayoutManager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public PhotoRecyclerAdapter getAdapter(){return mRecyclerAdapter;}

    @Override
    public void initSwipeRefreshWidget(){}

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar(){
    }

    @Override
    public void showSwipeRefreshWidget(){}

    @Override
    public void hideSwipeRefreshWidget(){}

    @Override
    public boolean isSwipeRefreshing(){return false;}

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
    public Context getContext(){return this.getActivity();}
}
