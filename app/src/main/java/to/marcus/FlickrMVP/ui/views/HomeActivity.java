package to.marcus.FlickrMVP.ui.views;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Created by marcus on 23/03/15
 */

public class HomeActivity extends FragmentActivity {
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
