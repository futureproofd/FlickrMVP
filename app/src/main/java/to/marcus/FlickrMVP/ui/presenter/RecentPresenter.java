package to.marcus.FlickrMVP.ui.presenter;

import android.os.Bundle;

/**
 * Created by marcus on 6/8/2015
 */
public interface RecentPresenter {

    public void onActivityCreated(Bundle savedInstanceState);

    public void onSaveInstanceState(Bundle savedInstanceState);

    public void onResume();

    public void onPause();

    public void onDestroy();

    public void onRefresh();

    public void requestNetworkPhotos();
}
