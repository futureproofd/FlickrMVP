package to.marcus.FlickrMVP.ui.views;

import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by marcus on 7/23/2015
 */

public interface PhotoLargeView extends BaseContextView{
    void showProgressBar();
    void hideProgressBar();
    void toggleFullScreen();
    ImageView getPhotoView();
}
