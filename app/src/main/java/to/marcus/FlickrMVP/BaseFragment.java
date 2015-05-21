package to.marcus.FlickrMVP;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by marcus on 31/03/15.
 * base fragment to perform injection using the activity object graph of its parent
 */

public class BaseFragment extends Fragment {

    @Override public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
}
