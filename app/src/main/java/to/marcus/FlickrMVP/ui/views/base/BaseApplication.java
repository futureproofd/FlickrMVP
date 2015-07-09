package to.marcus.FlickrMVP.ui.views.base;

import android.app.Application;
import android.content.Context;
import com.squareup.otto.Bus;
import javax.inject.Inject;
import dagger.ObjectGraph;
import to.marcus.FlickrMVP.R;
import to.marcus.FlickrMVP.data.PhotoCache;
import to.marcus.FlickrMVP.data.event.ApiRequestHandler;
import to.marcus.FlickrMVP.modules.Modules;
import to.marcus.FlickrMVP.network.ApiService;

/**
 * Created by marcus on 23/03/15
 */

public class BaseApplication extends Application {
    private ObjectGraph applicationGraph;
    @Inject Bus bus;
    @Inject ApiService apiService;
    @Inject PhotoCache photosCache;

    @Override
    public void onCreate(){
        super.onCreate();
        buildInitialObjectGraphAndInject();
        createApiRequestHandler();
    }

    public static BaseApplication get(Context c){
        return (BaseApplication) c.getApplicationContext();
    }

    private void createApiRequestHandler(){
        bus.register(new ApiRequestHandler(bus, apiService));
    }


    public void buildInitialObjectGraphAndInject(){
        applicationGraph = ObjectGraph.create(Modules.list(getString(R.string.api_key)));
        applicationGraph.inject(this);
    }

    //for extending graph
    public ObjectGraph createScopedGraph(Object... modules){
        return applicationGraph.plus(modules);
    }
}
