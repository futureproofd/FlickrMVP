package to.marcus.FlickrMVP.ui.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.modules.PhotoViewModule;
import to.marcus.FlickrMVP.ui.presenter.PhotoViewPresenter;
import to.marcus.FlickrMVP.ui.views.PhotoLargeView;
import to.marcus.FlickrMVP.ui.views.activity.HomeActivity;
import to.marcus.FlickrMVP.ui.views.base.BaseFragment;

/**
 * Created by marcus on 7/22/2015
 */

public class PhotoViewFragment extends BaseFragment implements PhotoLargeView {
    private final String TAG = PhotoViewFragment.class.getSimpleName();
    @Inject
    PhotoViewPresenter photoViewPresenter;
    ProgressBar mProgressBar;
    ImageView mImageView;
    String mUrl;

    public static PhotoViewFragment newInstance() {
        return new PhotoViewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        Bundle bundle = getArguments();
        mUrl = bundle.getString(getContext().getString(R.string.large_photo));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated");
        photoViewPresenter.onActivityCreated(savedInstanceState);
        photoViewPresenter.requestNetworkPhoto(mUrl);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentContainer,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View v = inflater.inflate(R.layout.fragment_photoview_layout, parentContainer, false);
        mImageView = (ImageView) v.findViewById(R.id.photo_large_view);
        return v;
    }

    //BaseFragment
    @Override
    public List<Object> getModules() {
        return Arrays.<Object>asList(
                new PhotoViewModule(this)
        );
    }

    /*
    View Implementations
     */
    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void dismissToolBar() {
        ((HomeActivity) getActivity()).dismissToolBar();
    }

    @Override
    public void dismissSlidingTabs() {
        ((HomeActivity) getActivity()).dismissSlidingTabs();
    }

    @Override
    public Context getContext() {
        return this.getActivity();
    }

    @Override
    public ImageView getPhotoView() {
        return mImageView;
    }

    @Override
    public ProgressBar getProgressBar() {
        return (mProgressBar = ((HomeActivity) getActivity()).getProgressBar());
    }

    @Override
    public void toggleFullScreen(){
        ((HomeActivity) getActivity()).toggleFullScreen();
    }

}
