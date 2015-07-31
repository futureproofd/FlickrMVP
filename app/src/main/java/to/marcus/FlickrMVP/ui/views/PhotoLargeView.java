package to.marcus.FlickrMVP.ui.views;

import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by mplienegger on 7/23/2015.
 */
public interface PhotoLargeView extends BaseContextView{
    void showProgressBar();
    void hideProgressBar();
    void dismissToolBar();
    void dismissSlidingTabs();
    void toggleFullScreen();
    ProgressBar getProgressBar();
    ImageView getPhotoView();
}
