package to.marcus.FlickrMVP.ui.views.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.WindowManager;
import android.widget.ProgressBar;
import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.ui.views.fragments.PhotoViewFragment;

/**
 * Created by marcus on 8/7/2015
 */

public class PhotoViewActivity extends FragmentActivity{
    private static final String TAG = PhotoViewActivity.class.getSimpleName();
    public ProgressBar mProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoview);
        initProgressBar();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.photo_large_view);
        if(fragment == null){
            fragment = new PhotoViewFragment();
            fragment.setArguments(getIntent().getExtras());
            fm.beginTransaction()
                    .add(R.id.photoview_container, fragment)
                    .commit();
        }
    }

    public ProgressBar getProgressBar(){
        return mProgressBar;
    }

    public void toggleFullScreen(){
        if((getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }else{
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public void initProgressBar(){
        mProgressBar = (ProgressBar)findViewById(R.id.progress_bar_photoview);
    }

    /*
    BaseContextView
    */
    public Context getContext(){
        return this.getApplicationContext();
    }
}
