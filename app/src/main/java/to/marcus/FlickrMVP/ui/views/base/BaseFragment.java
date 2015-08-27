package to.marcus.FlickrMVP.ui.views.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import java.util.Arrays;
import java.util.List;
import dagger.ObjectGraph;

/**
 * Created by marcus on 31/03/15.
 * base fragment to perform injection using the activity object graph of its parent
 */

public abstract class BaseFragment extends Fragment {
    private static final String TAG = BaseFragment.class.getSimpleName();
    private ObjectGraph mObjectGraph;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mObjectGraph = BaseApplication.get(getActivity())
                .createScopedGraph(getModules().toArray());
        mObjectGraph.inject(this);
    }

    protected List<Object> getModules(){
        return Arrays.<Object>asList(
                //override from extension fragment
        );
    }

    @Override
    public void onDestroy(){
        mObjectGraph = null;
        super.onDestroy();
    }

}
