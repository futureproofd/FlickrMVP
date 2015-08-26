package to.marcus.FlickrMVP.ui.presenter;

import android.os.Bundle;

/**
 * Created by marcus on 6/26/2015
 */

public interface SearchPresenter {
    public void onActivityCreated(Bundle savedInstanceState);

    public void onSaveInstanceState(Bundle savedInstanceState);

    public void onResume();

    public void onPause();

    public void onDestroy();

    public void onDestroyView();

    public void onRefresh();

    public void requestNetworkPhotos(String query);

    public void onNetworkPhotoSelected(String url);
}
