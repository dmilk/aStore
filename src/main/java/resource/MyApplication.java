package resource;

import filter.NewCrossOriginResourceSharingFilter;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Notreal
 */
@ApplicationPath("rest")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        packages("resource");
        register(NewCrossOriginResourceSharingFilter.class);
    }
}
