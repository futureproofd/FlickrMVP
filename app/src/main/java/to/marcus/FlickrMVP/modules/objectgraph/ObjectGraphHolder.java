package to.marcus.FlickrMVP.modules.objectgraph;

import android.app.Application;

import dagger.ObjectGraph;

/**
 * Created by mplienegger on 8/14/2015.
 */
public class ObjectGraphHolder {
    private static ObjectGraph objectGraph;
    private static ObjectGraphCreator objectGraphCreator;


    public static ObjectGraph getObjectGraph(Application application) {
        if (objectGraph == null) {
            objectGraph = objectGraphCreator.create(application);
        }
        return objectGraph;
    }


    public static void setObjectGraphCreator(ObjectGraphCreator objectGraphCreator) {
        if (ObjectGraphHolder.objectGraphCreator == null) {
            ObjectGraphHolder.objectGraphCreator = objectGraphCreator;
        }
    }


    public static void forceObjectGraphCreator(ObjectGraphCreator objectGraphCreator) {
        objectGraph = null;
        ObjectGraphHolder.objectGraphCreator = objectGraphCreator;
    }


    public static void inject(Application application, Object object) {
        getObjectGraph(application).inject(object);
    }


    public static ObjectGraph createScopedObjectGraph(Application application, Object... modules) {
        return ObjectGraphHolder.getObjectGraph(application).plus(modules);
    }
}