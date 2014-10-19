package rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author OLEG
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(rest.AuthSecurityInterceptor.class);
        resources.add(rest.CategoryREST.class);
        resources.add(rest.NewCrossOriginResourceSharingFilter.class);
        resources.add(rest.PurchaseREST.class);
        resources.add(rest.RouteREST.class);
        resources.add(rest.TestREST.class);
        resources.add(rest.TicketREST.class);
        resources.add(rest.UserREST.class);
        resources.add(rest.service.RouteFacadeREST.class);
    }
    
}
