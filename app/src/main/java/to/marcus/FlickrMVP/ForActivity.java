package to.marcus.FlickrMVP;

import java.lang.annotation.Retention;
import javax.inject.Qualifier;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by marcus on 31/03/15.
 */


@Qualifier @Retention(RUNTIME)
public @interface ForActivity {
}
