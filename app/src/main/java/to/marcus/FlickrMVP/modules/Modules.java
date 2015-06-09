package to.marcus.FlickrMVP.modules;

/**
 * Created by marcus on 4/15/2015
 *  * list of modules to use for the application graph
 * @return list
 */

public final class Modules{

    public static Object[] list(String apiKey) {
        return new Object[] {
            new ApplicationModule(apiKey)
        };
    }

    private Modules() {//no instances
    }
}
