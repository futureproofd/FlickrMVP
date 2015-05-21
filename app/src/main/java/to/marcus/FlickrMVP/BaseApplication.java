package to.marcus.FlickrMVP;

import android.app.Application;
import android.content.Context;
import com.squareup.otto.Bus;
import javax.inject.Inject;
import dagger.ObjectGraph;
import to.marcus.FlickrMVP.event.ApiRequestHandler;
import to.marcus.FlickrMVP.modules.Modules;
import to.marcus.FlickrMVP.network.ApiService;

/**
 * Created by marcus on 23/03/15
 */

public class BaseApplication extends Application {
    private ObjectGraph applicationGraph;
    @Inject Bus bus;
    @Inject ApiService apiService;

    @Override
    public void onCreate(){
        super.onCreate();
        buildObjectGraphAndInject();
        createApiRequestHandler();
    }

    public static BaseApplication get(Context c){
        return (BaseApplication) c.getApplicationContext();
    }

    private void createApiRequestHandler(){
        bus.register(new ApiRequestHandler(bus, apiService));
    }

    public void buildObjectGraphAndInject(){
        applicationGraph = ObjectGraph.create(Modules.list(getString(R.string.api_key)));
        applicationGraph.inject(this);
    }

    public ObjectGraph createScopedGraph(Object... modules){
        return applicationGraph.plus(modules);
    }
}
