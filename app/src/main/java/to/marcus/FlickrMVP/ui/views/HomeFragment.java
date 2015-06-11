package to.marcus.FlickrMVP.ui.views;

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
import to.marcus.FlickrMVP.ui.views.base.BaseFragment;
import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.model.Photos.Photo;
import to.marcus.FlickrMVP.modules.PresenterModule;
import to.marcus.FlickrMVP.network.PhotoHandler;
import to.marcus.FlickrMVP.ui.adapter.PhotoAdapter;
import to.marcus.FlickrMVP.ui.presenter.ImagePresenter;

/**
 * Created by marcus on 31/03/15!
 */

public class HomeFragment extends BaseFragment implements PhotosView {
    private final String TAG = HomeFragment.class.getSimpleName();
    GridView mGridView;
    ArrayList<Photo> receivedPhotosList;
    @Inject ImagePresenter presenter;
    PhotoHandler mResponseHandler;

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Override
    //Get BaseFragment scoped ObjectGraph
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
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
    public void onResume(){
        super.onResume();
        //get bus
        presenter.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        //destroy bus
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

    //BaseFragment
    @Override
    public List<Object> getModules(){
        return Arrays.<Object>asList(
                new PresenterModule(this)
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
        Log.i(TAG, "array received via presenter");
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
