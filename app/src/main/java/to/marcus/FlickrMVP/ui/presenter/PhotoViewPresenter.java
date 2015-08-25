package to.marcus.FlickrMVP.ui.presenter;

import android.os.Bundle;

/**
 * Created by marcus on 7/23/2015
 */

public interface PhotoViewPresenter {
    public void onActivityCreated(Bundle savedInstanceState);

    public void onSaveInstanceState(Bundle savedInstanceState);

    public void onResume();

    public void onPause();

    public void onDestroy();

    public void onDestroyView();

    public void onRefresh();

    public void requestNetworkPhoto(String url);
}
