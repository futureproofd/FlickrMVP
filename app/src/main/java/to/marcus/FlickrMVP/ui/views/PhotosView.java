package to.marcus.FlickrMVP.ui.views;

import java.util.ArrayList;
import to.marcus.FlickrMVP.model.Photo;
import to.marcus.FlickrMVP.ui.adapter.PhotoRecyclerAdapter;

/**
 * Created by marcus on 6/8/2015
 */

public interface PhotosView extends BaseContextView {
    void setPhotos(ArrayList<Photo> images);
    public ArrayList<Photo> getPhotoArray();
    void setGridViewAdapter(PhotoRecyclerAdapter adapter);
    public PhotoRecyclerAdapter getAdapter();
    void initSwipeRefreshWidget();
    void showProgressBar();
    void hideProgressBar();
    void showSwipeRefreshWidget();
    void hideSwipeRefreshWidget();
    boolean isSwipeRefreshing();
    void showWebViewPhotoFragment(String url);
}
