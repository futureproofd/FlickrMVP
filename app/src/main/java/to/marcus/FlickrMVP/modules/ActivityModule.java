package to.marcus.FlickrMVP.modules;
/**
import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import to.marcus.FlickrMVP.ui.views.base.BaseActivity;
import to.marcus.FlickrMVP.ForActivity;
import to.marcus.FlickrMVP.ui.views.HomeActivity;
import to.marcus.FlickrMVP.ui.views.HomeFragment;

/**
 * module only for objects existing on the scope of a single activity
 * can create activity specific singletons

CURRENTLY NOT NEEDED


@Module(
        injects ={
                HomeActivity.class,
                HomeFragment.class
        },
        includes ={
          PresenterModule.class
        },
        library = true
)
public class ActivityModule {
    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity){
        this.activity = activity;
    }

    /**
     * allow the activity context to be injected but annotated to differentiate from application
     * context
    */

/*
    @Provides @Singleton @ForActivity
    Context provideActivityContext(){
        return activity;
    }

}
*/