package to.marcus.FlickrMVP.ui.presenter;

import android.os.Bundle;

/**
 * Created by marcus on 8/11/2015
 */

public interface HistoryPresenter {
    public void onActivityCreated(Bundle savedInstanceState);

    public void onSaveInstanceState(Bundle savedInstanceState);

    public void onResume();

    public void onPause();

    public void onDestroy();

    public void onRefresh();

    public void onNetworkPhotoSelected(String url);

    public void onDeletePhotos();
}
