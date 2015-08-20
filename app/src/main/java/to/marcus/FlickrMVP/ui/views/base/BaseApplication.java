package to.marcus.FlickrMVP.ui.views.base;

import android.app.Application;
import android.content.Context;
import com.squareup.otto.Bus;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import dagger.ObjectGraph;
import to.marcus.FlickrMVP.data.PhotoCache;
import to.marcus.FlickrMVP.data.event.ApiRequestHandler;
import to.marcus.FlickrMVP.modules.ApplicationModule;
import to.marcus.FlickrMVP.modules.objectgraph.ObjectGraphCreator;
import to.marcus.FlickrMVP.modules.objectgraph.ObjectGraphHolder;
import to.marcus.FlickrMVP.network.ApiService;

/**
 * Created by marcus on 23/03/15
 */

public class BaseApplication extends Application {
    private ObjectGraph applicationGraph;
    @Inject Bus bus;
    @Inject ApiService apiService;
    @Inject PhotoCache photosCache;
    //added
    @Inject Context mAppContext;
    ObjectGraph mObjectGraph;

    @Override
    public void onCreate(){
        super.onCreate();
        buildInitialObjectGraphAndInject();
        createApiRequestHandler();
    }

    public static BaseApplication get(Context c){
        return (BaseApplication) c.getApplicationContext();
    }

    public Context getAppContext(){
        return mAppContext;
    }


    private void createApiRequestHandler(){
        bus.register(new ApiRequestHandler(bus, apiService));
    }

    public void buildInitialObjectGraphAndInject(){
        ObjectGraphHolder.setObjectGraphCreator(new ObjectGraphCreator() {
            @Override
            public ObjectGraph create(Application application) {
                return ObjectGraph.create(getModules().toArray());
            }
        });
        mObjectGraph = ObjectGraphHolder.getObjectGraph(this);
        mObjectGraph.inject(this);
    }

    //added
    public ObjectGraph createScopedGraph(Object... modules) {
        return ObjectGraphHolder.getObjectGraph(this).plus(modules);
    }

    //added
    private List<Object> getModules() {
        return Arrays.<Object>asList(new ApplicationModule(this));
    }

}
