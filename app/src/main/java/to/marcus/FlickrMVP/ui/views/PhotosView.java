package to.marcus.FlickrMVP.ui.views;

import java.util.ArrayList;
import to.marcus.FlickrMVP.model.Photo;
import to.marcus.FlickrMVP.ui.adapter.PhotoAdapter;

/**
 * Created by marcus on 6/8/2015
 */

public interface PhotosView extends BaseContextView {
    void setPhotos(ArrayList<Photo> images);
    public ArrayList<Photo> getPhotoArray();
    void setGridViewAdapter(PhotoAdapter adapter);
    void showProgressBar();
    void hideProgressBar();
}
