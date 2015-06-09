package to.marcus.FlickrMVP;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Created by marcus on 23/03/15
 */

public abstract class BaseActivity extends FragmentActivity {
    private final String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Activity Created");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

/*
CURRENTLY NOT NEEDED
    protected List<Object> getModules(){
        return Arrays.<Object>asList(new ActivityModule(this));
    }
*/
}
