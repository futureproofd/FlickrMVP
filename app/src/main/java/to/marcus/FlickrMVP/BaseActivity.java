package to.marcus.FlickrMVP;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import java.util.Arrays;
import java.util.List;
import to.marcus.FlickrMVP.modules.ActivityModule;

/**
 * Created by marcus on 23/03/15
 */
public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    protected List<Object> getModules(){
        return Arrays.<Object>asList(new ActivityModule(this));
    }

}
