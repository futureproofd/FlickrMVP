package to.marcus.FlickrMVP.ui;

import android.os.Bundle;
import android.util.Log;
import to.marcus.FlickrMVP.BaseActivity;

/**
 * Created by marcus on 23/03/15
 */

public class HomeActivity extends BaseActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Activity created");
        //after the super.onCreate call returns, we are guaranteed our injections are available
        //do something with injected dependencies here
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, HomeFragment.newInstance())
                    .commit();
        }
    }
}
