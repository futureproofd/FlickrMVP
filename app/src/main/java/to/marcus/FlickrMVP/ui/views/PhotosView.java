package to.marcus.FlickrMVP.ui.views;

import java.util.ArrayList;
import to.marcus.FlickrMVP.model.Photos;
import to.marcus.FlickrMVP.ui.adapter.PhotoAdapter;

/**
 * Created by marcus on 6/8/2015
 */

public interface PhotosView extends BaseContextView {
    void setPhotos(ArrayList<Photos.Photo> images);
    public ArrayList<Photos.Photo> getPhotoArray();
    void setGridViewAdapter(PhotoAdapter adapter);
}
